package com.htec.user_management.user.service.impl;

import com.htec.domain_starter.service.CrudService;
import com.htec.domain_starter.service.dto.converter.Convertible;
import com.htec.domain_starter.service.dto.converter.DtoConverter;
import com.htec.domain_starter.service.validation.chain.BusinessValidatorChain;
import com.htec.domain_starter.service.validation.exception.NotFoundException;
import com.htec.domain_starter.service.validation.marker.Create;
import com.htec.domain_starter.service.validation.marker.Update;
import com.htec.user_management.auth.service.RevokeTokenService;
import com.htec.user_management.user.repository.RoleRepository;
import com.htec.user_management.user.repository.UserRepository;
import com.htec.user_management.user.repository.entity.Role;
import com.htec.user_management.user.repository.entity.User;
import com.htec.user_management.user.service.UserService;
import com.htec.user_management.user.service.dto.RoleDto;
import com.htec.user_management.user.service.dto.UserDto;
import com.htec.user_management.user.service.dto.converter.RoleDtoConverter;
import com.htec.user_management.user.service.dto.converter.UserDtoConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static com.htec.domain_starter.common.constants.MessageSourceKeys.RESOURCE_DOES_NOT_EXIST;
import static com.htec.user_management.user.service.dto.RoleDto.Value.ROLE_REGULAR_USER;
import static java.util.stream.Collectors.toSet;
import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

/**
 * @author Nikola Stanar
 * <p>
 * Implementation of {@link UserService}.
 */
@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    /**
     * JPA repository for user.
     */
    private final UserRepository userRepository;

    /**
     * Dto converter for user.
     */
    private final UserDtoConverter userDtoConverter;

    /**
     * Business validator chain for user.
     */
    private final BusinessValidatorChain<UserDto> businessValidatorChain;

    /**
     * Jpa repository for role.
     */
    private final RoleRepository roleRepository;

    /**
     * Dto converter for role.
     */
    private final RoleDtoConverter roleDtoConverter;

    /**
     * Revoke token service.
     */
    private final RevokeTokenService revokeTokenService;

    /**
     * Message source.
     */
    private final MessageSource messageSource;

    /**
     * Creates user from dto.
     *
     * @param user User that is about to be created.
     * @return Created user.
     * @see UserService#createFrom(UserDto)
     */
    @Override
    public UserDto createFrom(@NotNull @Valid final UserDto user) {
        businessValidatorChain.validateFor(Create.class, user);
        final User userEntity = userDtoConverter.from(user);
        final Role regularUserRole = roleRepository.findByName(ROLE_REGULAR_USER.getName());

        // TODO: Adding new roles should be supported in future.
        userEntity.setRoles(Collections.singleton(regularUserRole));

        final User createdUser = userRepository.save(userEntity);

        return userDtoConverter.from(createdUser);
    }

    /**
     * Deletes user by id.
     *
     * @param id Id of the DTO.
     * @return Deleted user.
     * @see UserService#deleteById(Long)
     */
    @Override
    public UserDto deleteById(final Long id) {
        log.info("Deleting user of id {}.", id);
        return userRepository
                .findById(id)
                .map(user -> {
                    final UserDto deletedUser = getDtoConverter().from(user);
                    userRepository.deleteById(id);
                    //Logout user.
                    revokeTokenService.revokeFor(user.getUsername());
                    return deletedUser;
                }).orElseThrow(() -> {
                    final String message = messageSource.getMessage(RESOURCE_DOES_NOT_EXIST, new Object[]{id}, getLocale());
                    throw new NotFoundException(message);
                });
    }

    /**
     * Finds user by username.
     *
     * @param username User's username.
     * @return Optional user.
     * @see UserService#findByUsername(String)
     */
    @Override
    public Optional<UserDto> findByUsername(final @NotBlank String username) {
        log.info("Fetching details for user of username {}.", username);
        return userRepository
                .findByUsernameIgnoreCase(username)
                .map(userDtoConverter::from);
    }

    /**
     * Updates user of a given username.
     *
     * @param username User's username.
     * @param dto      Update content.
     * @return Updated user.
     * @see UserService#updateByUsername(String, UserDto)
     */
    @Override
    public UserDto updateByUsername(final @NotBlank String username, @NotNull @Valid final UserDto dto) {
        log.info("Updating user {} with content {}.", username, dto);
        businessValidatorChain.validateFor(Update.class, dto);
        return userRepository
                .findByUsernameIgnoreCase(username)
                .map(entity -> getRepository().save(getDtoConverter().from(dto, entity)))
                .map(getDtoConverter()::from)
                .orElseThrow(() -> {
                    final String message = getMessageSource().getMessage(RESOURCE_DOES_NOT_EXIST, new Object[]{username}, getLocale());
                    throw new NotFoundException(message);
                });
    }

    /**
     * Deletes user by username.
     *
     * @param username User's username.
     * @return Deleted user.
     * @see UserService#deleteByUsername(String)
     */
    @Override
    public UserDto deleteByUsername(@NotBlank final String username) {
        log.info("Deleting user of username {}.", username);
        return userRepository
                .findByUsernameIgnoreCase(username)
                .map(user -> {
                    final UserDto deletedUser = getDtoConverter().from(user);
                    userRepository.deleteByUsername(username);
                    //Logout user.
                    revokeTokenService.revokeFor(username);
                    return deletedUser;
                }).orElseThrow(() -> {
                    final String message = messageSource.getMessage(RESOURCE_DOES_NOT_EXIST, new Object[]{username}, getLocale());
                    throw new NotFoundException(message);
                });
    }

    /**
     * Finds user roles.
     *
     * @param userId Id ot the user.
     * @return User roles.
     * @see UserService#findRolesBy(Long)
     */
    @Override
    public Set<RoleDto> findRolesBy(@NotNull final Long userId) {
        log.info("Fetching roles for user of id {}.", userId);
        return userRepository
                .findById(userId)
                .map(user -> user
                        .getRoles()
                        .stream()
                        .map(roleDtoConverter::from)
                        .collect(toSet())).orElse(Collections.emptySet());
    }

    /**
     * Gets searchable repository.
     *
     * @return Check {@link JpaRepository}.
     * @see CrudService#getRepository()
     */
    @Override
    public JpaRepository<User, Long> getRepository() {
        return userRepository;
    }

    /**
     * Gets business validator chain.
     *
     * @return Business validator chain.
     * @see UserService#getBusinessValidatorChain()
     */
    @Override
    public Optional<BusinessValidatorChain<UserDto>> getBusinessValidatorChain() {
        return Optional.ofNullable(businessValidatorChain);
    }

    /**
     * Gets message source.
     *
     * @return Message source.
     * @see UserService#getMessageSource()
     */
    @Override
    public MessageSource getMessageSource() {
        return messageSource;
    }

    /**
     * Gets dto converter.
     *
     * @return Check {@link DtoConverter}.
     * @see Convertible#getDtoConverter()
     */
    @Override
    public DtoConverter<UserDto, User> getDtoConverter() {
        return userDtoConverter;
    }
}

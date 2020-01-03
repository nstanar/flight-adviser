package com.htec.user_management.user.service.impl;

import com.htec.domain_starter.service.CrudService;
import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.domain_starter.service.dto.converter.Convertible;
import com.htec.domain_starter.service.dto.converter.DtoConverter;
import com.htec.domain_starter.service.validation.exception.BusinessValidationException;
import com.htec.user_management.user.repository.RoleRepository;
import com.htec.user_management.user.repository.UserRepository;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.htec.user_management.common.constants.MessageSourceKeys.USERNAME_ALREADY_EXISTS;
import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

/**
 * @author Nikola Stanar
 * <p>
 * Implementation of {@link UserService}.
 */
@Service
@Transactional
@Validated
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
     * Jpa repository for role.
     */
    private final RoleRepository roleRepository;

    /**
     * Dto converter for role.
     */
    private final RoleDtoConverter roleDtoConverter;

    /**
     * Message source.
     */
    private final MessageSource messageSource;

    /**
     * Creates user from dto.
     *
     * @param user User that is about to be created.
     * @return Created user.
     * @see CrudService#createFrom(BaseDto)
     */
    @Override
    //TODO: revise how to deal with uniqueness, since it is a cross cutting concern.
    public UserDto createFrom(@NotNull @Valid final UserDto user) {
        /* Check if username already exists. */
        final Optional<User> optionalExistingUser = userRepository.findByUsername(user.getUsername());
        if (optionalExistingUser.isPresent()) {
            final String message = messageSource.getMessage(USERNAME_ALREADY_EXISTS, new Object[]{}, getLocale());
            throw new BusinessValidationException(message);
        }

        final User userEntity = userDtoConverter.from(user);
        final User createdUser = userRepository
                .save(userEntity);
        return userDtoConverter.from(createdUser);
    }

    /**
     * Finds user by username.
     *
     * @param username User's username.
     * @return Optional user.
     * @see UserService#findBy(String)
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> findBy(final @NotBlank String username) {
        log.info("Fetching details for user of username {}.", username);
        return userRepository
                .findByUsername(username)
                .map(userDtoConverter::from);
    }

    /**
     * Finds user roles.
     *
     * @param userId Id ot the user.
     * @return User roles.
     */
    public List<RoleDto> findRolesBy(@NotNull final Long userId) {
        return roleRepository
                .findAllByUserId(userId)
                .stream()
                .map(roleDtoConverter::from)
                .collect(Collectors.toList());
    }

    /**
     * Gets searchable repository.
     *
     * @return Check {@link JpaRepository}.
     * @see CrudService#getUserRepository()
     */
    @Override
    public JpaRepository<User, Long> getUserRepository() {
        return userRepository;
    }

    /**
     * Gets dto converter.
     *
     * @return Check {@link DtoConverter}.
     * @see Convertible#getUserDtoConverter()
     */
    @Override
    public DtoConverter<UserDto, User> getUserDtoConverter() {
        return userDtoConverter;
    }
}

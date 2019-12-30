package com.htec.user_management.user.service.impl;

import com.htec.domain_starter.service.validation.exception.BusinessValidationException;
import com.htec.user_management.user.repository.UserRepository;
import com.htec.user_management.user.repository.entity.User;
import com.htec.user_management.user.service.UserService;
import com.htec.user_management.user.service.dto.UserDto;
import com.htec.user_management.user.service.dto.converter.UserDtoConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

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
     * JPA repository.
     */
    private final UserRepository repository;

    /**
     * Dto converter.
     */
    private final UserDtoConverter dtoConverter;

    /**
     * Message source.
     */
    private final MessageSource messageSource;

    /**
     * Finds page of users.
     *
     * @param pageable Check {@link Pageable}.
     * @return Page of users.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UserDto> findAll(@NotNull final Pageable pageable) {
        log.info("Fetching users with request: {}", pageable);
        return repository.findAll(pageable)
                .map(dtoConverter::from);
    }

    /**
     * Finds user by id.
     *
     * @param id User's id.
     * @return User having id.
     * @see UserService#findBy(Long)
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> findBy(@NotNull final Long id) {
        log.info("Fetching user of id {}.", id);
        return repository.findById(id)
                .map(dtoConverter::from);
    }

    /**
     * Creates user from dto.
     *
     * @param user User that is about to be created.
     * @return Created user.
     * @see UserService#create(UserDto)
     */
    @Override
    public UserDto create(@NotNull @Valid final UserDto user) {
        log.info("Creating user: {}", user);
        /* Check if username already exists. */
        final Optional<User> optionalExistingUser = repository.findByUsername(user.getUsername());
        if (optionalExistingUser.isPresent()) {
            final String message = messageSource.getMessage(USERNAME_ALREADY_EXISTS, new Object[]{}, getLocale());
            throw new BusinessValidationException(message);
        }

        final User userEntity = dtoConverter.from(user);
        final User createdUser = repository.save(userEntity);
        log.info("User successfully created and given id {}.", createdUser.getId());
        return dtoConverter.from(createdUser);
    }

}

package com.htec.user_management.user.service.impl;

import com.htec.domain_starter.service.CrudService;
import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.domain_starter.service.dto.converter.Convertible;
import com.htec.domain_starter.service.dto.converter.DtoConverter;
import com.htec.domain_starter.service.validation.exception.BusinessValidationException;
import com.htec.user_management.user.repository.UserRepository;
import com.htec.user_management.user.repository.entity.User;
import com.htec.user_management.user.service.UserService;
import com.htec.user_management.user.service.dto.UserDto;
import com.htec.user_management.user.service.dto.converter.UserDtoConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.repository.JpaRepository;
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
     * Creates user from dto.
     *
     * @param user User that is about to be created.
     * @return Created user.
     * @see CrudService#create(BaseDto)
     */
    @Override
    //TODO: revise how to deal with uniqueness, since it is a cross cutting concern.
    public Long create(@NotNull @Valid final UserDto user) {
        /* Check if username already exists. */
        final Optional<User> optionalExistingUser = repository.findByUsername(user.getUsername());
        if (optionalExistingUser.isPresent()) {
            final String message = messageSource.getMessage(USERNAME_ALREADY_EXISTS, new Object[]{}, getLocale());
            throw new BusinessValidationException(message);
        }

        final User userEntity = dtoConverter.from(user);
        return repository
                .save(userEntity)
                .getId();
    }

    /**
     * Gets searchable repository.
     *
     * @return Check {@link JpaRepository}.
     * @see CrudService#getRepository()
     */
    @Override
    public JpaRepository<User, Long> getRepository() {
        return repository;
    }

    /**
     * Gets dto converter.
     *
     * @return Check {@link DtoConverter}.
     * @see Convertible#getDtoConverter()
     */
    @Override
    public DtoConverter<UserDto, User> getDtoConverter() {
        return dtoConverter;
    }
}

package com.htec.user_management.user.service.validation.validator.impl;

import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.domain_starter.service.validation.exception.BusinessValidationException;
import com.htec.domain_starter.service.validation.validator.BusinessValidator;
import com.htec.user_management.user.repository.UserRepository;
import com.htec.user_management.user.service.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

/**
 * @author Nikola Stanar
 * <p>
 * Validates uniqueness of the username.
 */
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class UsernameUniquenessValidator implements BusinessValidator<UserDto> {

    /**
     * Jpa repository for user.
     */
    private final UserRepository userRepository;

    /**
     * Message source.
     */
    private final MessageSource messageSource;

    /**
     * Message source key.
     */
    public static final String USERNAME_ALREADY_EXISTS = "username_already_exists";

    /**
     * Validates that username is unique.
     *
     * @param dto DTO to be validated
     * @see BusinessValidator#validate(BaseDto)
     */
    @Override
    public void validate(final @NotNull UserDto dto) {
        final String username = dto.getUsername();
        final boolean alreadyExists = userRepository.existsByUsernameIgnoreCase(username);
        if (alreadyExists) {
            final String message = messageSource.getMessage(USERNAME_ALREADY_EXISTS, new Object[]{username}, getLocale());
            throw new BusinessValidationException(message);
        }
    }

}

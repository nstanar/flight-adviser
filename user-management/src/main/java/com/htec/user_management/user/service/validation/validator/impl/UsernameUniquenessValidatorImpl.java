package com.htec.user_management.user.service.validation.validator.impl;

import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.domain_starter.service.validation.exception.BusinessValidationException;
import com.htec.user_management.user.repository.UserRepository;
import com.htec.user_management.user.repository.entity.User;
import com.htec.user_management.user.service.dto.UserDto;
import com.htec.user_management.user.service.validation.UsernameUniquenessValidator;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

/**
 * @author Nikola Stanar
 * <p>
 * Validates uniqueness of the username.
 * @see UsernameUniquenessValidator
 */
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class UsernameUniquenessValidatorImpl implements UsernameUniquenessValidator {

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
     * @see UsernameUniquenessValidator#validate(BaseDto)
     */
    @Override
    public void validate(final @NotNull UserDto dto) {
        final Long id = dto.getId();
        final String username = dto.getUsername();
        final Optional<User> optionalUser = userRepository.findByUsernameIgnoreCase(username);

        boolean alreadyExists = false;
        if (optionalUser.isPresent()) {
            final User user = optionalUser.get();
            if (id != null) {
                // Existing user.
                if (!user.getId().equals(id)) {
                    // Existing user with different id and same username.
                    alreadyExists = true;
                }
            } else {
                // Completely new user with existing username.
                alreadyExists = true;
            }
        }

        if (alreadyExists) {
            final String message = messageSource.getMessage(USERNAME_ALREADY_EXISTS, new Object[]{username}, getLocale());
            throw new BusinessValidationException(message);
        }


    }

}

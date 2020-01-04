package com.htec.user_management.user.service.validation.validator.impl;

import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.domain_starter.service.util.PasswordShredder;
import com.htec.domain_starter.service.validation.exception.BusinessValidationException;
import com.htec.user_management.user.service.dto.UserDto;
import com.htec.user_management.user.service.validation.PasswordMatcherValidator;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

/**
 * @author Nikola Stanar
 * <p>
 * Validates that passwords (original and retyped) match.
 * @see PasswordMatcherValidator
 */
@Component
@AllArgsConstructor
public class PasswordMatcherValidatorImpl implements PasswordMatcherValidator {

    /**
     * Char array shredder.
     */
    private final PasswordShredder passwordShredder;

    /**
     * Message source.
     */
    private final MessageSource messageSource;

    /**
     * Used as message source key.
     */
    public static final String PASSWORDS_DO_NOT_MATCH = "passwords_do_not_match";

    /**
     * Validates password matches new password.
     *
     * @param dto DTO to be validated
     * @see PasswordMatcherValidator#validate(BaseDto)
     */
    @Override
    public void validate(@NotNull final UserDto dto) {
        final char[] password = dto.getPassword();
        final char[] newPassword = dto.getRetypedPassword();
        if (!Arrays.equals(password, newPassword)) {
            passwordShredder.shred(password);
            passwordShredder.shred(newPassword);
            final String message = messageSource.getMessage(PASSWORDS_DO_NOT_MATCH, new Object[]{}, LocaleContextHolder.getLocale());
            throw new BusinessValidationException(message);
        }

        passwordShredder.shred(newPassword);
    }

}

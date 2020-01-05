package com.htec.user_management.user.service.validation.validator.impl;

import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.domain_starter.service.util.PasswordShredder;
import com.htec.domain_starter.service.validation.util.ExceptionUtil;
import com.htec.user_management.user.service.dto.UserDto;
import com.htec.user_management.user.service.validation.PasswordMatcherValidator;
import lombok.AllArgsConstructor;
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
     * Exception util.
     */
    private final ExceptionUtil exceptionUtil;

    /**
     * Used as message source key.
     */
    public static final String SECRETS_DO_NOT_MATCH = "secrets_do_not_match";

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
            throw exceptionUtil.createBusinessValidationExceptionFrom(SECRETS_DO_NOT_MATCH, new Object[]{});
        }

        passwordShredder.shred(newPassword);
    }

}

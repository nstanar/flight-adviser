package com.htec.user_management.user.service.validation;

import com.htec.domain_starter.service.validation.validator.BusinessValidator;
import com.htec.user_management.user.service.dto.UserDto;

/**
 * @author Nikola Stanar
 * <p>
 * Validates that passwords (original and retyped) match.
 * @see BusinessValidator
 */
@FunctionalInterface
public interface PasswordMatcherValidator extends BusinessValidator<UserDto> {

}

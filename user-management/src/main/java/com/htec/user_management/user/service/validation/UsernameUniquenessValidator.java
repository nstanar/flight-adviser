package com.htec.user_management.user.service.validation;

import com.htec.domain_starter.service.validation.validator.BusinessValidator;
import com.htec.user_management.user.service.dto.UserDto;

/**
 * @author Nikola Stanar
 * <p>
 * Validates uniqueness of the username.
 * @see BusinessValidator
 */
@FunctionalInterface
public interface UsernameUniquenessValidator extends BusinessValidator<UserDto> {

}

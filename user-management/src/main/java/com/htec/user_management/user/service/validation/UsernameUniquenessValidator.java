package com.htec.user_management.user.service.validation;

import com.htec.domain_starter.service.validation.util.UniquenessValidatorTemplate;
import com.htec.domain_starter.service.validation.validator.BusinessValidator;
import com.htec.user_management.user.repository.entity.User;
import com.htec.user_management.user.service.dto.UserDto;

/**
 * @author Nikola Stanar
 * <p>
 * Validates uniqueness of the username.
 * @see BusinessValidator
 */
public interface UsernameUniquenessValidator extends UniquenessValidatorTemplate<UserDto, User, Long> {

}

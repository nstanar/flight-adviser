package com.htec.user_management.user.service.validation.validator.impl;

import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.domain_starter.service.validation.util.ExceptionUtil;
import com.htec.user_management.user.repository.UserRepository;
import com.htec.user_management.user.repository.entity.User;
import com.htec.user_management.user.service.dto.UserDto;
import com.htec.user_management.user.service.validation.UsernameUniquenessValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Optional;

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
     * Exception util.
     */
    private final ExceptionUtil exceptionUtil;

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
        UsernameUniquenessValidator.super.validate(id, optionalUser.get(), USERNAME_ALREADY_EXISTS, new Object[]{username});
    }

    /**
     * Gets exception util.
     *
     * @return Exception util.
     * @see UsernameUniquenessValidator#getExceptionUtil()
     */
    @Override
    public ExceptionUtil getExceptionUtil() {
        return exceptionUtil;
    }

}

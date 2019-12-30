package com.htec.user_management.user.service.dto.converter.impl;

import com.htec.domain_starter.repository.entity.BaseEntity;
import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.user_management.user.repository.entity.User;
import com.htec.user_management.user.service.dto.UserDto;
import com.htec.user_management.user.service.dto.converter.UserDtoConverter;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

import static java.nio.CharBuffer.wrap;

/**
 * @author Nikola Stanar
 * <p>
 * Implemenation of {@link UserDtoConverter}.
 */
@Component
@Validated
@AllArgsConstructor
public class UserDtoConverterImpl implements UserDtoConverter {

    /**
     * Password encoder.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Converts user entity to dto.
     *
     * @param entity Entity to be converted.
     * @return Check {@link UserDto}.
     * @see UserDtoConverter#from(BaseEntity)
     */
    @Override
    public UserDto from(@NotNull final User entity) {
        final UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword().toCharArray());
        return dto;
    }

    /**
     * Converts user dto to entity.
     *
     * @param dto DTO to be converted.
     * @return Check {@link User}.
     * @see UserDtoConverter#from(BaseDto)
     */
    @Override
    public User from(@NotNull final UserDto dto) {
        return from(dto, new User());
    }

    /**
     * Converts to user entity from dto and existing entity.
     *
     * @param dto            DTO to be converted.
     * @param existingEntity Existing ENTITY.
     * @return Check {@link User}.
     * @see UserDtoConverter#from(BaseDto, BaseEntity)
     */
    @Override
    public User from(@NotNull final UserDto dto, @NotNull final User existingEntity) {
        existingEntity.setFirstName(dto.getFirstName());
        existingEntity.setLastName(dto.getLastName());
        existingEntity.setUsername(dto.getUsername());

        /* Encode password */
        final char[] password = dto.getPassword();
        existingEntity.setPassword(passwordEncoder.encode(wrap(password)));
        clearPasswordField(password);

        return existingEntity;
    }

    /**
     * Clears password field.
     *
     * @param password Password.
     */
    private void clearPasswordField(final char[] password) {
        Arrays.fill(password, '\u0000');
    }
}

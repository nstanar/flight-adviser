package com.htec.user_management.user.service.dto.converter.impl;

import com.htec.domain_starter.repository.entity.BaseEntity;
import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.domain_starter.service.util.PasswordShredder;
import com.htec.user_management.user.repository.entity.User;
import com.htec.user_management.user.service.dto.UserDto;
import com.htec.user_management.user.service.dto.converter.UserDtoConverter;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

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
     * For password shredding.
     */
    private final PasswordShredder passwordShredder;

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
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedBy(entity.getModifiedBy());
        dto.setModifiedDate(entity.getModifiedDate());
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
        final User entity = new User();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setUsername(dto.getUsername());

        // Encode password.
        final char[] password = dto.getPassword();
        entity.setPassword(passwordEncoder.encode(wrap(password)));

        // Shred password.
        passwordShredder.shred(password);

        return entity;
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

        // Encode password.
        final char[] password = dto.getPassword();
        existingEntity.setPassword(passwordEncoder.encode(wrap(password)));

        // Shred password.
        passwordShredder.shred(password);

        return existingEntity;
    }
}

package com.htec.user_management.user.service.dto.converter;

import com.htec.domain_starter.repository.entity.BaseEntity;
import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.domain_starter.service.dto.converter.DtoConverter;
import com.htec.user_management.user.repository.entity.Role;
import com.htec.user_management.user.service.dto.RoleDto;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @author Nikola Stanar
 * <p>
 * Dto converter for {@link RoleDto} to {@link Role} and vice-versa.
 */
@Component
@NoArgsConstructor
public class RoleDtoConverter implements DtoConverter<RoleDto, Role> {

    /**
     * Converts from role entity to dto.
     *
     * @param entity Entity to be converted.
     * @return Role dto.
     * @see DtoConverter#from(BaseEntity)
     */
    @Override
    public RoleDto from(@NotNull final Role entity) {
        final RoleDto dto = new RoleDto();
        dto.setId(entity.getId());
        dto.setValue(RoleDto.Value.valueOf(entity.getName()));
        return dto;
    }

    /**
     * Converts from role dto to entity.
     *
     * @param dto DTO to be converted.
     * @return Role entity.
     * @see DtoConverter#from(BaseDto)
     */
    @Override
    public Role from(@NotNull final RoleDto dto) {
        return from(dto, new Role());
    }

    /**
     * Maps values from dto to existing entity.
     *
     * @param dto            DTO to be converted.
     * @param existingEntity Existing ENTITY.
     * @return Entity with updated values.
     * @see DtoConverter#from(BaseDto, BaseEntity)
     */
    @Override
    public Role from(@NotNull final RoleDto dto, @NotNull final Role existingEntity) {
        existingEntity.setName(dto.getValue().getName());
        return existingEntity;
    }
}

package com.htec.domain_starter.service.dto.converter;

import com.htec.domain_starter.repository.entity.BaseEntity;
import com.htec.domain_starter.service.dto.BaseDto;

import javax.validation.constraints.NotNull;

/**
 * @author Nikola Stanar
 * <p>
 * Interface for DTO converters.
 */
public interface DtoConverter<DTO extends BaseDto, ENTITY extends BaseEntity> {

    /**
     * From ENTITY to DTO.
     *
     * @param entity Entity to be converted.
     * @return DTO.
     */
    DTO from(@NotNull ENTITY entity);

    /**
     * From DTO to ENTITY.
     *
     * @param dto DTO to be converted.
     * @return ENTITY.
     */
    ENTITY from(@NotNull DTO dto);

    /**
     * From DTO and existing ENTITY to ENTITY.
     *
     * @param dto            DTO to be converted.
     * @param existingEntity Existing ENTITY.
     * @return ENTITY.
     */
    ENTITY from(@NotNull DTO dto, @NotNull ENTITY existingEntity);

}

package com.htec.domain_starter.service.dto.converter;

import com.htec.domain_starter.repository.BaseEntity;
import com.htec.domain_starter.service.dto.BaseDto;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author Nikola Stanar
 * <p>
 * Interface for DTO converters.
 */
@Validated
public interface DtoConverter<D extends BaseDto<ID>, E extends BaseEntity<ID>, ID> {

    /**
     * From ENTITY to DTO.
     *
     * @param entity Entity to be converted.
     * @return DTO.
     */
    D from(@NotNull E entity);

    /**
     * From DTO to ENTITY.
     *
     * @param dto DTO to be converted.
     * @return ENTITY.
     */
    E from(@NotNull D dto);

    /**
     * From DTO and existing ENTITY to ENTITY.
     *
     * @param dto            DTO to be converted.
     * @param existingEntity Existing ENTITY.
     * @return ENTITY.
     */
    E from(@NotNull D dto, @NotNull E existingEntity);

}

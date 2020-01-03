package com.htec.domain_starter.service.dto.converter;

import com.htec.domain_starter.repository.entity.BaseEntity;
import com.htec.domain_starter.service.dto.BaseDto;

/**
 * @author Nikola Stanar
 * <p>
 * Convertable.
 */
@FunctionalInterface
public interface Convertible<DTO extends BaseDto, ENTITY extends BaseEntity> {

    /**
     * Gets dto converter.
     *
     * @return Check {@link DtoConverter}.
     */
    DtoConverter<DTO, ENTITY> getUserDtoConverter();

}

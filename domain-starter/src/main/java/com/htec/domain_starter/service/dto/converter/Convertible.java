package com.htec.domain_starter.service.dto.converter;

import com.htec.domain_starter.repository.BaseEntity;
import com.htec.domain_starter.service.dto.BaseDto;

/**
 * @author Nikola Stanar
 * <p>
 * Convertable.
 */
@FunctionalInterface
public interface Convertible<D extends BaseDto<ID>, E extends BaseEntity<ID>, ID> {

    /**
     * Gets dto converter.
     *
     * @return Check {@link DtoConverter}.
     */
    DtoConverter<D, E, ID> getDtoConverter();

}

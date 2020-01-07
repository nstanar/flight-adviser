package com.htec.flight_management.service.dto.converter.impl;

import com.htec.flight_management.repository.entity.Country;
import com.htec.flight_management.service.dto.CountryDto;
import com.htec.flight_management.service.dto.converter.CountryDtoConverter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @author Nikola Stanar
 * <p>
 * Dto converter for {@link CountryDto} to {@link Country} and vice-versa.
 * @see CountryDtoConverter
 */
@Component
public class CountryDtoConverterImpl implements CountryDtoConverter {

    /**
     * Converts country entity to dto.
     *
     * @param entity Entity to be converted.
     * @return Country dto.
     */
    @Override
    public CountryDto from(@NotNull final Country entity) {
        final CountryDto dto = new CountryDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    /**
     * Converts country dto to entity.
     *
     * @param dto DTO to be converted.
     * @return Country entity.
     */
    @Override
    public Country from(@NotNull final CountryDto dto) {
        return from(dto, new Country());
    }

    /**
     * Maps values from country dto to existing entity.
     *
     * @param dto            DTO to be converted.
     * @param existingEntity Existing ENTITY.
     * @return Existing entity with updated values.
     */
    @Override
    public Country from(@NotNull final CountryDto dto, @NotNull final Country existingEntity) {
        existingEntity.setName(dto.getName());
        return existingEntity;
    }
}

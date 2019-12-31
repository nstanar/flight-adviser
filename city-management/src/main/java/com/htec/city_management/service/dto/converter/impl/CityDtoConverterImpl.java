package com.htec.city_management.service.dto.converter.impl;

import com.htec.city_management.repository.entity.City;
import com.htec.city_management.repository.entity.Country;
import com.htec.city_management.service.dto.CityDto;
import com.htec.city_management.service.dto.converter.CityDtoConverter;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author Nikola Stanar
 * <p>
 * Dto converter for converting {@link CityDto} to {@link City} and vice-versa.
 */
@Component
@Validated
public class CityDtoConverterImpl implements CityDtoConverter {

    /**
     * Converts city entity to dto.
     *
     * @param entity Entity to be converted.
     * @return City dto.
     */
    @Override
    public CityDto from(@NotNull final City entity) {
        final CityDto dto = new CityDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        final Country country = entity.getCountry();
        dto.setCountryId(country.getId());
        dto.setCountryName(country.getName());
        return dto;
    }

    /**
     * Converts city dto to entity.
     *
     * @param dto DTO to be converted.
     * @return City entity.
     */
    @Override
    public City from(@NotNull final CityDto dto) {
        return from(dto, new City());
    }

    /**
     * Maps values from city dto to existing entity.
     *
     * @param dto            DTO to be converted.
     * @param existingEntity Existing ENTITY.
     * @return Existing entity with updated values.
     */
    @Override
    public City from(@NotNull final CityDto dto, @NotNull final City existingEntity) {
        existingEntity.setName(dto.getName());
        existingEntity.setDescription(dto.getDescription());
        return existingEntity;
    }
}

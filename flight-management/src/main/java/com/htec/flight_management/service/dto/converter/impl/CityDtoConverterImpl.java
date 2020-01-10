package com.htec.flight_management.service.dto.converter.impl;

import com.htec.domain_starter.service.validation.util.ExceptionUtil;
import com.htec.flight_management.repository.CountryRepository;
import com.htec.flight_management.repository.entity.City;
import com.htec.flight_management.repository.entity.Country;
import com.htec.flight_management.service.dto.CityDto;
import com.htec.flight_management.service.dto.converter.CityDtoConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Optional;

import static com.htec.domain_starter.common.constants.MessageSourceKeys.RESOURCE_DOES_NOT_EXIST;

/**
 * @author Nikola Stanar
 * <p>
 * Dto converter for converting {@link CityDto} to {@link City} and vice-versa.
 */
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class CityDtoConverterImpl implements CityDtoConverter {

    /**
     * Repository for country.
     */
    private final CountryRepository countryRepository;

    /**
     * Exception util.
     */
    private final ExceptionUtil exceptionUtil;

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
        dto.setCountryId(entity.getCountry().getId());
        dto.setCountryName(entity.getCountry().getName());
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
        final City city = new City();
        city.setName(dto.getName());
        city.setDescription(dto.getDescription());
        final Long countryId = dto.getCountryId();
        final Optional<Country> optionalCountry = countryRepository.findById(countryId);

        final Country country;
        if (optionalCountry.isPresent()) {
            country = optionalCountry.get();
            city.setCountry(country);
        } else {
            throw exceptionUtil.createNotFoundExceptionFrom(RESOURCE_DOES_NOT_EXIST, new Object[]{countryId});
        }

        return city;
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

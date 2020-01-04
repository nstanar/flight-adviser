package com.htec.city_management.service.dto.converter.impl;

import com.htec.city_management.repository.CountryRepository;
import com.htec.city_management.repository.entity.City;
import com.htec.city_management.repository.entity.Country;
import com.htec.city_management.service.dto.CityDto;
import com.htec.city_management.service.dto.converter.CityDtoConverter;
import com.htec.domain_starter.service.validation.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Optional;

import static com.htec.domain_starter.common.constants.MessageSourceKeys.RESOURCE_DOES_NOT_EXIST;
import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

/**
 * @author Nikola Stanar
 * <p>
 * Dto converter for converting {@link CityDto} to {@link City} and vice-versa.
 */
@Service
@Transactional(readOnly = true)
@Validated
@AllArgsConstructor
public class CityDtoConverterImpl implements CityDtoConverter {

    /**
     * Jpa repository for country.
     */
    private final CountryRepository countryRepository;

    /**
     * Message source.
     */
    private final MessageSource messageSource;

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
        final City entity = new City();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        final Long countryId = dto.getCountryId();
        final Optional<Country> optionalCountry = countryRepository.findById(countryId);
        if (optionalCountry.isPresent()) {
            entity.setCountry(optionalCountry.get());
        } else {
            final String message = messageSource.getMessage(RESOURCE_DOES_NOT_EXIST, new Object[]{countryId}, getLocale());
            throw new NotFoundException(message);
        }
        return entity;
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

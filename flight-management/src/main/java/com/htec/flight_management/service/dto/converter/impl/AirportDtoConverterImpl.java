package com.htec.flight_management.service.dto.converter.impl;

import com.htec.domain_starter.service.validation.util.ExceptionUtil;
import com.htec.flight_management.repository.CityRepository;
import com.htec.flight_management.repository.entity.Airport;
import com.htec.flight_management.repository.entity.City;
import com.htec.flight_management.service.dto.AirportDto;
import com.htec.flight_management.service.dto.converter.AirportDtoConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Optional;

import static com.htec.domain_starter.common.constants.MessageSourceKeys.RESOURCE_DOES_NOT_EXIST;

/**
 * @author Nikola Stanar
 * <p>
 * Dto converter from {@link AirportDto} to {@link Airport} and vice versa.
 * @see AirportDtoConverter
 */
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class AirportDtoConverterImpl implements AirportDtoConverter {

    /**
     * Repository for city.
     */
    private final CityRepository cityRepository;

    /**
     * Exception util.
     */
    private final ExceptionUtil exceptionUtil;

    /**
     * Converts airport entity to dto.
     *
     * @param entity Entity to be converted.
     * @return Airport dto.
     */
    @Override
    public AirportDto from(@NotNull final Airport entity) {
        final AirportDto dto = new AirportDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setIataCode(entity.getIataCode());
        dto.setIcaoCode(entity.getIcaoCode());
        dto.setCityId(entity.getCity().getId());
        return dto;
    }

    /**
     * Converts airport dto to entity.
     *
     * @param dto DTO to be converted.
     * @return Airport entity.
     */
    @Override
    public Airport from(@NotNull final AirportDto dto) {
        final Airport entity = new Airport();
        entity.setName(dto.getName());
        entity.setName(dto.getName());
        entity.setIataCode(dto.getIataCode());
        entity.setIcaoCode(dto.getIcaoCode());
        final Long cityId = dto.getCityId();
        final Optional<City> optionalCity = cityRepository.findById(cityId);

        if (optionalCity.isPresent()) {
            final City city = optionalCity.get();
            entity.setCity(city);
        } else {
            throw exceptionUtil.createNotFoundExceptionFrom(RESOURCE_DOES_NOT_EXIST, new Object[]{cityId});
        }

        return entity;
    }

    /**
     * Maps content from airport dto to existing entity.
     *
     * @param dto            DTO to be converted.
     * @param existingEntity Existing ENTITY.
     * @return Airport entity with updated content.
     */
    @Override
    public Airport from(@NotNull final AirportDto dto, @NotNull final Airport existingEntity) {
        existingEntity.setName(dto.getName());
        existingEntity.setName(dto.getName());
        existingEntity.setIataCode(dto.getIataCode());
        existingEntity.setIcaoCode(dto.getIcaoCode());
        return existingEntity;
    }

}

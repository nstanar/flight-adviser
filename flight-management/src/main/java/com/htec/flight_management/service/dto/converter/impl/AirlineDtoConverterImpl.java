package com.htec.flight_management.service.dto.converter.impl;

import com.htec.domain_starter.repository.BaseEntity;
import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.flight_management.repository.entity.Airline;
import com.htec.flight_management.service.dto.AirlineDto;
import com.htec.flight_management.service.dto.converter.AirlineDtoConverter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @author Nikola Stanar
 * <p>
 * Dto converter for {@link AirlineDto} to {@link Airline} and vice-versa.
 * @see AirlineDtoConverter
 */
@Component
public class AirlineDtoConverterImpl implements AirlineDtoConverter {

    /**
     * Converts airline entity to dto.0
     *
     * @param entity Entity to be converted.
     * @return Airline dto.
     * @see AirlineDtoConverter#from(BaseEntity)
     */
    @Override
    public AirlineDto from(@NotNull final Airline entity) {
        final AirlineDto dto = new AirlineDto();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        return dto;
    }

    /**
     * Converts airline dto to entity.
     *
     * @param dto DTO to be converted.
     * @return Airline entity.
     * @see AirlineDtoConverter#from(BaseDto)
     */
    @Override
    public Airline from(@NotNull final AirlineDto dto) {
        return from(dto, new Airline());
    }

    /**
     * Maps content from dto to existing arline entity. 
     *
     * @param dto            DTO to be converted.
     * @param existingEntity Existing ENTITY.
     * @return Existing entity with updated content.
     * @see AirlineDtoConverter#from(BaseDto, BaseEntity)
     */
    @Override
    public Airline from(@NotNull final AirlineDto dto, @NotNull final Airline existingEntity) {
        existingEntity.setCode(dto.getCode());
        return existingEntity;
    }
}

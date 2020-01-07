package com.htec.flight_management.service.dto.converter.impl;

import com.htec.flight_management.repository.entity.Airline;
import com.htec.flight_management.service.dto.AirlineDto;
import com.htec.flight_management.service.dto.converter.AirlineDtoConverter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @author Nikola Stanar
 * <p>
 * Dto converter for {@link AirlineDto} to {@link Airline} and vice-versa.
 */
@Component
public class AirlineDtoConverterImpl implements AirlineDtoConverter {

    /**
     * 
     * @param entity Entity to be converted.
     * @return
     */
    @Override
    public AirlineDto from(@NotNull final Airline entity) {
        return null;
    }

    @Override
    public Airline from(@NotNull final AirlineDto dto) {
        return null;
    }

    @Override
    public Airline from(@NotNull final AirlineDto dto, @NotNull final Airline existingEntity) {
        return null;
    }
}

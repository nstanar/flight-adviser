package com.htec.flight_management.service.dto.converter.impl;

import com.htec.domain_starter.repository.BaseEntity;
import com.htec.domain_starter.service.validation.util.ExceptionUtil;
import com.htec.flight_management.repository.AirlineRepository;
import com.htec.flight_management.repository.AirportRepository;
import com.htec.flight_management.repository.entity.Airline;
import com.htec.flight_management.repository.entity.Airport;
import com.htec.flight_management.repository.entity.Flight;
import com.htec.flight_management.service.dto.FlightDto;
import com.htec.flight_management.service.dto.converter.AirlineDtoConverter;
import com.htec.flight_management.service.dto.converter.AirportDtoConverter;
import com.htec.flight_management.service.dto.converter.FlightDtoConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Optional;

import static com.htec.domain_starter.common.constants.MessageSourceKeys.RESOURCE_DOES_NOT_EXIST;

/**
 * @author Nikola Stanar
 * <p>
 * Dto converter for {@link FlightDto} to {@link Flight} and vice-versa.
 * @see FlightDtoConverter
 */
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class FlightDtoConverterImpl implements FlightDtoConverter {

    /**
     * Repository for airport.
     */
    private final AirportRepository airportRepository;

    /**
     * Airport dto converter.
     */
    private final AirportDtoConverter airportDtoConverter;

    /**
     * Repository for airline.
     */
    private final AirlineRepository airlineRepository;

    /**
     * Airline dto converter.
     */
    private final AirlineDtoConverter airlineDtoConverter;

    /**
     * Exception util.
     */
    private final ExceptionUtil exceptionUtil;

    /**
     * Converts flight entity to dto.
     *
     * @param entity Entity to be converted.
     * @return Flight dto.
     * @see FlightDtoConverter#from(BaseEntity)
     */
    @Override
    public FlightDto from(@NotNull final Flight entity) {
        final FlightDto dto = new FlightDto();
        dto.setId(entity.getId());
        dto.setSourceAirportId(entity.getSource().getId());
        dto.setDestinationAirportId(entity.getDestination().getId());
        dto.setDestination(airportDtoConverter.from(entity.getDestination()));
        dto.setAirlineId(entity.getAirline().getId());
        dto.setAirline(airlineDtoConverter.from(entity.getAirline()));
        dto.setPrice(entity.getPrice());
        dto.setStops(entity.getStops());
        return dto;
    }

    /**
     * Converts flight dto to entity.
     *
     * @param dto DTO to be converted.
     * @return Flight entity.
     */
    @Override
    public Flight from(@NotNull final FlightDto dto) {
        final Flight entity = new Flight();
        entity.setStops(dto.getStops());
        entity.setPrice(dto.getPrice());

        final Long sourceId = dto.getSourceAirportId();
        final Optional<Airport> optionalSourceAirport = airportRepository.findById(sourceId);

        // Set source.
        if (optionalSourceAirport.isPresent()) {
            final Airport airport = optionalSourceAirport.get();
            entity.setSource(airport);
        } else {
            throw exceptionUtil.createNotFoundExceptionFrom(RESOURCE_DOES_NOT_EXIST, new Object[]{sourceId});
        }

        // Set destination.
        final Long destinationId = dto.getDestinationAirportId();
        final Optional<Airport> optionalDestinationAirport = airportRepository.findById(destinationId);

        if (optionalDestinationAirport.isPresent()) {
            final Airport airport = optionalDestinationAirport.get();
            entity.setDestination(airport);
        } else {
            throw exceptionUtil.createNotFoundExceptionFrom(RESOURCE_DOES_NOT_EXIST, new Object[]{sourceId});
        }

        // Set airline.
        final Long airlineId = dto.getAirlineId();
        final Optional<Airline> optionalAirline = airlineRepository.findById(airlineId);

        if (optionalAirline.isPresent()) {
            final Airline airline = optionalAirline.get();
            entity.setAirline(airline);
        } else {
            throw exceptionUtil.createNotFoundExceptionFrom(RESOURCE_DOES_NOT_EXIST, new Object[]{sourceId});
        }


        return entity;
    }

    /**
     * Maps content from flight dto to existing entity.
     *
     * @param dto            DTO to be converted.
     * @param existingEntity Existing ENTITY.
     * @return Existing entity with updated content.
     */
    @Override
    public Flight from(@NotNull final FlightDto dto, @NotNull final Flight existingEntity) {
        existingEntity.setStops(dto.getStops());
        existingEntity.setPrice(dto.getPrice());
        return existingEntity;
    }

}

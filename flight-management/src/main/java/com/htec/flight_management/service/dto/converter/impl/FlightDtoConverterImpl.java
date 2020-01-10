package com.htec.flight_management.service.dto.converter.impl;

import com.htec.domain_starter.repository.BaseEntity;
import com.htec.domain_starter.service.validation.util.ExceptionUtil;
import com.htec.flight_management.repository.AirportRepository;
import com.htec.flight_management.repository.entity.Airport;
import com.htec.flight_management.repository.entity.Flight;
import com.htec.flight_management.service.dto.FlightDto;
import com.htec.flight_management.service.dto.converter.FlightDtoConverter;
import com.htec.flight_management.service.util.DistanceCalculator;
import lombok.AllArgsConstructor;
import org.gavaghan.geodesy.GlobalPosition;
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
     * Distance calculator.
     */
    private final DistanceCalculator distanceCalculator;

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

        // Set source.
        final Airport source = entity.getSource();
        dto.setSourceAirportId(source.getId());

        //Set destination.
        final Airport destination = entity.getDestination();
        dto.setDestinationAirportId(destination.getId());

        // Set distance.
        final GlobalPosition sourcePosition = new GlobalPosition(source.getLatitude(), source.getLongitude(), 0.0);
        final GlobalPosition destinationPosition = new GlobalPosition(destination.getLatitude(), destination.getLongitude(), 0.0);
        dto.setDistanceInKm(distanceCalculator.calculateBetween(sourcePosition, destinationPosition));

        dto.setAirlineCode(entity.getAirlineCode());
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
        entity.setAirlineCode(dto.getAirlineCode());
        entity.setStops(dto.getStops());
        entity.setPrice(dto.getPrice());

        final Long sourceId = dto.getSourceAirportId();
        final Optional<Airport> optionalSourceAirport = airportRepository.findById(sourceId);

        final Airport source;
        final Airport destination;

        // Set source.
        if (optionalSourceAirport.isPresent()) {
            source = optionalSourceAirport.get();
            entity.setSource(source);
        } else {
            throw exceptionUtil.createNotFoundExceptionFrom(RESOURCE_DOES_NOT_EXIST, new Object[]{sourceId});
        }

        // Set destination.
        final Long destinationId = dto.getDestinationAirportId();
        final Optional<Airport> optionalDestinationAirport = airportRepository.findById(destinationId);

        if (optionalDestinationAirport.isPresent()) {
            destination = optionalDestinationAirport.get();
            entity.setDestination(destination);
        } else {
            throw exceptionUtil.createNotFoundExceptionFrom(RESOURCE_DOES_NOT_EXIST, new Object[]{destinationId});
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
        existingEntity.setAirlineCode(dto.getAirlineCode());
        existingEntity.setStops(dto.getStops());
        existingEntity.setPrice(dto.getPrice());
        return existingEntity;
    }

}

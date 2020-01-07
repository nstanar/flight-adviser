package com.htec.flight_management.service.dto.converter;

import com.htec.domain_starter.service.dto.converter.DtoConverter;
import com.htec.flight_management.repository.entity.Flight;
import com.htec.flight_management.service.dto.FlightDto;

/**
 * @author Nikola Stanar
 * <p>
 * Dto converter for {@link FlightDto} to {@link Flight} and vice-versa.
 */
public interface FlightDtoConverter extends DtoConverter<FlightDto, Flight> {

}

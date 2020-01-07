package com.htec.flight_management.service.dto.converter;

import com.htec.domain_starter.service.dto.converter.DtoConverter;
import com.htec.flight_management.repository.entity.Airline;
import com.htec.flight_management.service.dto.AirlineDto;

/**
 * @author Nikola Stanar
 * <p>
 * Dto converter for {@link AirlineDto} to {@link Airline} and vice-versa.
 */
public interface AirlineDtoConverter extends DtoConverter<AirlineDto, Airline, Long> {

}

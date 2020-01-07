package com.htec.flight_management.service.dto.converter;

import com.htec.domain_starter.service.dto.converter.DtoConverter;
import com.htec.flight_management.repository.entity.Airport;
import com.htec.flight_management.service.dto.AirportDto;

/**
 * @author Nikola Stanar
 * <p>
 * Dto converter from {@link AirportDto} to {@link Airport} and vice versa.
 */
public interface AirportDtoConverter extends DtoConverter<AirportDto, Airport, Long> {

}

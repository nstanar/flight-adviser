package com.htec.flight_management.service;

import com.htec.domain_starter.service.PagingAndSortingService;
import com.htec.flight_management.repository.entity.Flight;
import com.htec.flight_management.service.dto.FlightDto;

/**
 * @author Nikola Stanar
 * <p>
 * Service exposing operations over flight.
 */
public interface FlightService extends PagingAndSortingService<FlightDto, Flight, Long> {

}

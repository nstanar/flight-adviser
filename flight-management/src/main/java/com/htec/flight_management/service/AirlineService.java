package com.htec.flight_management.service;

import com.htec.domain_starter.service.PagingAndSortingService;
import com.htec.flight_management.repository.entity.Airline;
import com.htec.flight_management.service.dto.AirlineDto;

/**
 * @author Nikola Stanar
 * <p>
 * Service exposing operations over airline.
 */
public interface AirlineService extends PagingAndSortingService<AirlineDto, Airline> {

}

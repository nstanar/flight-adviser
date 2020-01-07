package com.htec.flight_management.repository;

import com.htec.flight_management.repository.entity.Flight;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Nikola Stanar
 * <p>
 * Repository for flight.
 */
public interface FlightRepository extends PagingAndSortingRepository<Flight, Long> {

}

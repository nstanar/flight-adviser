package com.htec.flight_management.repository;

import com.htec.flight_management.repository.entity.Airline;
import com.htec.flight_management.repository.entity.City;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Nikola Stanar
 * <p>
 * Repository for airline.
 */
public interface AirlineRepository extends PagingAndSortingRepository<Airline, Long> {

    /**
     * Finds airline with given code.
     *
     * @param code Airline code.
     * @return Optional airline.
     */
    @Transactional(readOnly = true)
    Optional<Airline> findByCodeIgnoreCase(final String code);

}

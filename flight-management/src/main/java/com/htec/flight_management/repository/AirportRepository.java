package com.htec.flight_management.repository;

import com.htec.domain_starter.repository.SearchableRepository;
import com.htec.flight_management.repository.entity.Airport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Nikola Stanar
 * <p>
 * Repository for airport.
 */
public interface AirportRepository extends SearchableRepository<Airport, Long> {

    /**
     * Finds page of airports by city id.
     *
     * @param cityId   City id.
     * @param pageable Check {@link Pageable}.
     * @return Page of airports.
     */
    @Transactional(readOnly = true)
    Page<Airport> findAllByCityId(final Long cityId, final Pageable pageable);

    /**
     * Finds airport with given code.
     *
     * @param code Airport code.
     * @return Optional airport.
     */
    @Transactional(readOnly = true)
    Optional<Airport> findByCodeIgnoreCase(final String code);

}

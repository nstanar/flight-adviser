package com.htec.flight_management.repository;

import com.htec.flight_management.repository.entity.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Nikola Stanar
 * <p>
 * Repository for flight.
 */
public interface FlightRepository extends PagingAndSortingRepository<Flight, Long> {

    /**
     * Finds page of flights by source airport id.
     *
     * @param sourceId Source airport id.
     * @param pageable Check pageable.
     * @return Page of flights matching source airport id.
     */
    @Transactional(readOnly = true)
    Page<Flight> findAllBySourceId(final Long sourceId, final Pageable pageable);

}

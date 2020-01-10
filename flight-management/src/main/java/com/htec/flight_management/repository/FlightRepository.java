package com.htec.flight_management.repository;

import com.htec.flight_management.repository.entity.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Nikola Stanar
 * <p>
 * Repository for flight.
 */
@Transactional
public interface FlightRepository extends PagingAndSortingRepository<Flight, Long> {

    /**
     * Finds page of flights by source airport id.
     *
     * @param sourceId Source airport id.
     * @param pageable Check pageable.
     * @return Page of flights matching source airport id.
     */
    @Transactional(readOnly = true)
    @Query(value = "MATCH (source:Airport)-[r:FLIES_TO]->(destination:Airport) " +
            "WHERE id(source) = {sourceId} " +
            "RETURN source, destination, r")
    Page<Flight> findAllBySourceId(final Long sourceId, final Pageable pageable);

}

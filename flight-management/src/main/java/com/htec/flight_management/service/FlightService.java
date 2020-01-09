package com.htec.flight_management.service;

import com.htec.domain_starter.service.PagingAndSortingService;
import com.htec.flight_management.repository.entity.Flight;
import com.htec.flight_management.service.dto.FlightDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Nikola Stanar
 * <p>
 * Service exposing operations over flight.
 */
public interface FlightService extends PagingAndSortingService<FlightDto, Flight> {

    /**
     * Finds page of flights by source airport id.
     *
     * @param sourceId Source airport id.
     * @param pageable Check pageable.
     * @return Page of flights matching source airport id.
     */
    @Transactional(readOnly = true)
    Page<FlightDto> findBySourceId(final Long sourceId, final Pageable pageable);

}

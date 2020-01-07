package com.htec.flight_management.service;

import com.htec.domain_starter.service.PagingAndSortingService;
import com.htec.flight_management.repository.entity.Airport;
import com.htec.flight_management.service.dto.AirportDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

/**
 * @author Nikola Stanar
 * <p>
 * Service exposing operations over airport.
 */
public interface AirportService extends PagingAndSortingService<AirportDto, Airport, Long> {

    /**
     * Finds page of airports belonging to city of certain id.
     *
     * @param cityId   Id of the city.
     * @param pageable Check {@link Pageable}.
     * @return Page of airports.
     */
    @PreAuthorize("isAuthenticated()")
    @Transactional(readOnly = true)
    Page<AirportDto> findAllByCityId(@NotNull final Long cityId, @NotNull final Pageable pageable);

}

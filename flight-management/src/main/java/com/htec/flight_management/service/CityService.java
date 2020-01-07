package com.htec.flight_management.service;

import com.htec.domain_starter.service.SearchableService;
import com.htec.flight_management.repository.entity.City;
import com.htec.flight_management.service.dto.CityDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

/**
 * @author Nikola Stanar
 * <p>
 * Service for operations over city.
 */
public interface CityService extends SearchableService<CityDto, City> {

    /**
     * Finds page of cities belonging to country of id.
     *
     * @param countryId Id of the country.
     * @param pageable  Check {@link Pageable}.
     * @return Page of cities.
     */
    @PreAuthorize("isAuthenticated()")
    @Transactional(readOnly = true)
    Page<CityDto> findAllByCountryId(@NotNull final Long countryId, @NotNull final Pageable pageable);

}

package com.htec.flight_management.service;

import com.htec.flight_management.service.dto.ItineraryDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * @author Nikola Stanar
 * <p>
 * Service operating on itineraries.
 */
@Transactional
@Validated
public interface ItineraryService {

    /**
     * Finds cheapest itinerary between source and destination city.
     *
     * @param sourceCityId      Id of the source city.
     * @param destinationCityId Id of the destination city.
     * @return Optional cheapest itinerary.
     */
    @Transactional(readOnly = true)
    @PreAuthorize("isAuthenticated()")
    Optional<ItineraryDto> findCheapestBetweenCities(@NotNull final Long sourceCityId, @NotNull final Long destinationCityId);

}

package com.htec.flight_management.service.dto.converter;

import com.htec.flight_management.service.client.dto.CheapestRouteDto;
import com.htec.flight_management.service.dto.ItineraryDto;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Nikola Stanar
 * <p>
 * Itinerary dto converter.
 */
@FunctionalInterface
@Validated
public interface ItineraryDtoConverter {

    /**
     * Converts from iterable of shortest path records to itinerary dto.
     *
     * @param cheapestFlight Iterable of cheapest route.
     * @return Itinerary dto
     */
    ItineraryDto from(@NotEmpty final List<@NotNull CheapestRouteDto> cheapestFlight);

}

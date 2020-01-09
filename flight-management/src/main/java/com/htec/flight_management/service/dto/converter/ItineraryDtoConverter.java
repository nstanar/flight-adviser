package com.htec.flight_management.service.dto.converter;

import com.htec.flight_management.service.dto.AirportShortestPathRecordDto;
import com.htec.flight_management.service.dto.ItineraryDto;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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
     * @param shortestPath Iterable of shortest path records.
     * @return Itinerary dto..
     */
    ItineraryDto from(@NotEmpty @Size(min = 2) final List<AirportShortestPathRecordDto> shortestPath);

}

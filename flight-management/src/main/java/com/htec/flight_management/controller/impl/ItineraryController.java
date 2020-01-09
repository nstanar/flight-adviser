package com.htec.flight_management.controller.impl;

import com.htec.flight_management.service.ItineraryService;
import com.htec.flight_management.service.dto.ItineraryDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Nikola Stanar
 * <p>
 * Rest controller exposing operaties over itinerary.
 */
@RestController
@RequestMapping("/itineraries")
@AllArgsConstructor
public class ItineraryController {

    /**
     * Itinerary service.
     */
    private final ItineraryService service;

    /**
     * Find cheapest itinerary between source and destination city.
     *
     * @param sourceCityId      Id of the source city.
     * @param destinationCityId Id of the destination city.
     * @return 200 with optional itinerary if successful; 400 if source or destination do not exist.
     */
    @GetMapping("/cheapest/from/{sourceCityId}/to/{destinationCityId}")
    public ResponseEntity<ItineraryDto> findCheapestBetween(@PathVariable final Long sourceCityId, @PathVariable final Long destinationCityId) {
        ItineraryDto result = ItineraryDto.builder().build();

        final Optional<ItineraryDto> optionalItinerary = service.findCheapestBetweenCities(sourceCityId, destinationCityId);
        if (optionalItinerary.isPresent()) {
            result = optionalItinerary.get();
        }

        return ResponseEntity.ok(result);
    }

}

package com.htec.flight_management.service;

import com.htec.flight_management.service.dto.AirportShortestPathRecordDto;

import java.util.List;

/**
 * @author Nikola Stanar
 * <p>
 * Service interface for finding the shortest path between airports.
 */
public interface AirportsShortestPathService {

    /**
     * Finds cheapest path between source and destination airport.
     *
     * @param sourceAirportId      Source airport id.
     * @param destinationAirportId Destination airport id.
     * @return Iterable of {@link AirportShortestPathRecordDto}.
     */
    List<AirportShortestPathRecordDto> findCheapestBetween(final Long sourceAirportId, final Long destinationAirportId);

}

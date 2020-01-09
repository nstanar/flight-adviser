package com.htec.flight_management.service.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Nikola Stanar
 * <p>
 * Airport shortest path record.
 */
@Getter
@Builder
public class AirportShortestPathRecordDto {

    /**
     * Id.
     */
    private final Long id;

    /**
     * Airport name.
     */
    private final String name;

    /**
     * Airport IATA code.
     */
    private final String iataCode;

    /**
     * Airport ICAO code;
     */
    private final String icaoCode;

    /**
     * City name.
     */
    private final String city;

    /**
     * Country name.
     */
    private final String country;

    /**
     * Total cost.
     */
    private final double totalCost;

}

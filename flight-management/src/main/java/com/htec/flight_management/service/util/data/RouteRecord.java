package com.htec.flight_management.service.util.data;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Nikola Stanar
 * <p>
 * Class representing route record.
 */
@Getter
@Builder
@ToString
public class RouteRecord {

    /**
     * 2-letter (IATA) or 3-letter (ICAO) code of the airline.
     */
    private final String airlineCode;

    /**
     * 3-letter (IATA) or 4-letter (ICAO) code of the source airport.
     */
    private final String sourceAirportCode;

    /**
     * 3-letter (IATA) or 4-letter (ICAO) code of the source airport.
     */
    private final String destinationAirportCode;

    /**
     * 0 for direct.
     */
    private final int stops;

    /**
     * Flight price.
     */
    private final double price;

}

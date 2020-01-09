package com.htec.flight_management.service.util.data;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Nikola Stanar
 * <p>
 * Class representing aiport record.
 */
@Getter
@Builder
@ToString
public class AirportRecord {

    /**
     * Name.
     */
    private final String name;

    /**
     * City name.
     */
    private final String cityName;

    /**
     * Country name.
     */
    private final String countryName;

    /**
     * 3-letter IATA code. Null if not assigned/unknown.
     */
    private final String iataCode;

    /**
     * 4-letter ICAO code. Null if not assigned.
     */
    private final String icaoCode;

    /**
     * Latitude.
     */
    private final double latitude;

    /**
     * Longitude.
     */
    private final double longitude;

}

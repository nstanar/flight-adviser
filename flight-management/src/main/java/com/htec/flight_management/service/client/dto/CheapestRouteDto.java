package com.htec.flight_management.service.client.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author Nikola Stanar
 * <p>
 * Dto class representing cheapes flight.
 */
@Getter
@Builder
public class CheapestRouteDto {

    /**
     * Source airport id.
     */
    private final Long sourceAirportId;

    /**
     * Destination airport id.
     */
    private final Long destinationAirportId;

    /**
     * Price.
     */
    private final BigDecimal price;

    /**
     * Airline code.
     */
    private final String airlineCode;

    /**
     * Stops.
     */
    private final int stops;

}

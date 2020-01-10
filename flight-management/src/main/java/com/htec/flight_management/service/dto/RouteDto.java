package com.htec.flight_management.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Nikola Stanar
 * <p>
 * Dto class representing route.
 */
@Getter
@Setter
@NoArgsConstructor
public class RouteDto {

    /**
     * Source airport.
     */
    private String source;

    /**
     * Destination airport.
     */
    private String destination;

    /**
     * Airline code.
     */
    private String airlineCode;

    /**
     * Price for this route.
     */
    private BigDecimal price;

    /**
     * Distance in km between source and destination airport.
     */
    private double distanceInKm;

}

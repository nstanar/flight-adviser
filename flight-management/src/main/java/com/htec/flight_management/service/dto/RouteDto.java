package com.htec.flight_management.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
     * Price for this route.
     */
    private double price;

}

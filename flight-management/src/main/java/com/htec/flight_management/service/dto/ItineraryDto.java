package com.htec.flight_management.service.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Nikola Stanar
 * <p>
 * Dto representing itinerary.
 */
@Getter
@Builder
public class ItineraryDto {

    /**
     * Total price.
     */
    private final BigDecimal totalPrice;

    /**
     * Length.
     */
    private final double totalDistanceInKm;

    /**
     * Routes.
     */
    private final List<RouteDto> routes;

}

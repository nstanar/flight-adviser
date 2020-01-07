package com.htec.flight_management.controller.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

/**
 * @author Nikola Stanar
 * <p>
 * Representation model for flight.
 */
@Getter
@Builder
@Relation(itemRelation = "flight", collectionRelation = "flights")
public class FlightModel extends RepresentationModel<FlightModel> {

    /**
     * Source airport.
     */
    private final AirportModel source;

    /**
     * Destination airport.
     */
    private final AirportModel destination;

    /**
     * Airline.
     */
    private final AirlineModel airline;

    /**
     * Number of stops.
     * 0 for direct flight.
     */
    private final Integer stops;

    /**
     * Price.
     */
    private final BigDecimal price;

}

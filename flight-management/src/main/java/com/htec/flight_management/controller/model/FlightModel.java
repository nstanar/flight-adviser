package com.htec.flight_management.controller.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

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
     * Destination airport id.
     */
    private final Long destinationAirportId;

    /**
     * 2-letter (IATA) or 3-letter (ICAO) code of the airline.
     */
    private final String airlineCode;

    /**
     * Number of stops.
     * 0 for direct flight.
     */
    private final Integer stops;

    /**
     * Price.
     */
    private final Double price;

    /**
     * Distance between source and destination airports.
     */
    private final double distanceInKm;

}

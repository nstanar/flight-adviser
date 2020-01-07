package com.htec.flight_management.controller.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

/**
 * @author Nikola Stanar
 * <p>
 * Representation model for airport.
 */
@Getter
@Builder
@Relation(itemRelation = "airport", collectionRelation = "airports")
public class AirportModel extends RepresentationModel<AirportModel> {

    /**
     * Name.
     */
    private final String name;

    /**
     * 3-letter IATA code. Null if not assigned/unknown.
     * OR
     * 4-letter ICAO code. Null if not assigned/unknown.
     */
    private final String code;

    /**
     * Name of the city airport belongs to.
     */
    private final String cityName;

    /**
     * Name of the country airport belongs to.
     */
    private final String countryName;

}

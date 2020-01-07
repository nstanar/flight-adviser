package com.htec.flight_management.controller.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

/**
 * @author Nikola Stanar
 * <p>
 * Representation model for airline.
 */
@Getter
@Builder
@Relation(itemRelation = "airline", collectionRelation = "airlines")
public class AirlineModel extends RepresentationModel<AirlineModel> {

    /**
     * Airline code.
     */
    private final String code;

}

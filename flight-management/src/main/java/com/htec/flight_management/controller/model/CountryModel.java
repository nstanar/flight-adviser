package com.htec.flight_management.controller.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

/**
 * @author Nikola Stanar
 * <p>
 * Representation model for country.
 */
@Getter
@Builder
@Relation(itemRelation = "country", collectionRelation = "countries")
public class CountryModel extends RepresentationModel<CountryModel> {

    /**
     * Name of the country.
     */
    private final String name;

}

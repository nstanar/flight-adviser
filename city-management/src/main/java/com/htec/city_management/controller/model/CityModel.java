package com.htec.city_management.controller.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

/**
 * @author Nikola Stanar
 * <p>
 * Representation model for city.
 */
@Getter
@Builder
@Relation(collectionRelation = "cities")
public class CityModel extends RepresentationModel<CityModel> {

    /**
     * City name.
     */
    private final String name;

    /**
     * Description of the city.
     */
    private final String description;

    /**
     * Name of the country city resides in.
     */
    private final String countryName;

}

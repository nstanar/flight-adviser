package com.htec.city_management.controller.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

/**
 * @author Nikola Stanar
 * <p>
 * Representation model for country.
 */
@Getter
@Builder
public class CountryModel extends RepresentationModel<CountryModel> {

    /**
     * Name of the country.
     */
    private final String name;

}

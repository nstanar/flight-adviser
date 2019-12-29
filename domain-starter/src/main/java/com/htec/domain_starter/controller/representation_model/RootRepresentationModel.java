package com.htec.domain_starter.controller.representation_model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

/**
 * @author Nikola Stanar
 * <p>
 * Root representation model for application entry-point.
 */
@Getter
@Builder
public class RootRepresentationModel extends RepresentationModel<RootRepresentationModel> {

    /**
     * Description.
     */
    private final String description;
}

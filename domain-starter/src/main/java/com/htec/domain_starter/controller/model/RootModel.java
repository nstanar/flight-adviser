package com.htec.domain_starter.controller.model;

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
public class RootModel extends RepresentationModel<RootModel> {

    /**
     * Description.
     */
    private final String description;
}

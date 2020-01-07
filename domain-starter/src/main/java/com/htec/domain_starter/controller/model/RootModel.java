package com.htec.domain_starter.controller.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

/**
 * @author Nikola Stanar
 * <p>
 * Root representation model for application entry-point.
 */
@Getter
@Builder
@Relation(itemRelation = "root")
public class RootModel extends RepresentationModel<RootModel> {

    /**
     * Description.
     */
    private final String description;
}

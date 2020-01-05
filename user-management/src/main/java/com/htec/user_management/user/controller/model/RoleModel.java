package com.htec.user_management.user.controller.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

/**
 * @author Nikola Stanar
 * <p>
 * Representation model for role.
 */
@Getter
@Builder
@Relation(collectionRelation = "roles")
@EqualsAndHashCode
public class RoleModel extends RepresentationModel<RoleModel> {

    /**
     * Role name.
     */
    private final String name;

}

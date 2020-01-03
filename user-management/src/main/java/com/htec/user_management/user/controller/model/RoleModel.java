package com.htec.user_management.user.controller.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

/**
 * @author Nikola Stanar
 * <p>
 * Representation model for role.
 */
@Getter
@Builder
public class RoleModel extends RepresentationModel<RoleModel> {

    /**
     * Role name.
     */
    private final String name;

}

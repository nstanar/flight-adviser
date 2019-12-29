package com.htec.user_management.user.controller.representation_model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

/**
 * @author Nikola Stanar
 * <p>
 * Representation model of a user.
 */
@Getter
@Builder
public class UserRepresentationModel extends RepresentationModel<UserRepresentationModel> {

    /**
     * User's first name.
     */
    private final String firstName;

    /**
     * User's last name.
     */
    private final String lastName;

    /**
     * User's username.
     */
    private final String username;

}

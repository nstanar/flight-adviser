package com.htec.user_management.user.controller.model;

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
public class UserModel extends RepresentationModel<UserModel> {

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

package com.htec.user_management.user.controller.model;

import com.htec.domain_starter.controller.model.AuditAwareModel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.hateoas.server.core.Relation;

/**
 * @author Nikola Stanar
 * <p>
 * Representation model of a user.
 */
@Getter
@Relation(collectionRelation = "users")
public class UserModel extends AuditAwareModel<UserModel> {

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

    /**
     * Constructor.
     *
     * @param createdDate  Created date.
     * @param modifiedDate Modified date.
     * @param createdBy    Created by.
     * @param modifiedBy   Modified by.
     * @param firstName    First name.
     * @param lastName     Last name.
     * @param username     Username.
     */
    @Builder
    public UserModel(final Long createdDate, final Long modifiedDate, final String createdBy, final String modifiedBy, final String firstName, final String lastName, final String username) {
        super(createdDate, modifiedDate, createdBy, modifiedBy);
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
    }
}

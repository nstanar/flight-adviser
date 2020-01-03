package com.htec.user_management.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Nikola Stanar
 * <p>
 * Class holding names for hypermedia relations.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class HypermediaRelNames {

    /**
     * Defines users relation.
     */
    public static final String USERS_REL_NAME = "users";

    /**
     * Defines "having roles" relation.
     */
    public static final String HAVING_ROLES = "havingRoles";
}

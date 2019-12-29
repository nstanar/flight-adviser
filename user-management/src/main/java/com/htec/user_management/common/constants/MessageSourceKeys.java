package com.htec.user_management.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Nikola Stanar
 * <p>
 * Class holding message source keys.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MessageSourceKeys {

    /**
     * Used to indicate that user does not exist.
     */
    public static final String USER_DOES_NOT_EXIST = "user_does_not_exist";

    /**
     * Used to indicate that username already exists.
     */
    public static final String USERNAME_ALREADY_EXISTS = "username_already_exists";

}

package com.htec.city_management.common.constants;

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
     * Used to indicate that country does not exist.
     */
    public static final String COUNTRY_DOES_NOT_EXIST = "country_does_not_exist";

    /**
     * Used to indicate that city does not exist.
     */
    public static final String CITY_DOES_NOT_EXIST = "city_does_not_exist";
}

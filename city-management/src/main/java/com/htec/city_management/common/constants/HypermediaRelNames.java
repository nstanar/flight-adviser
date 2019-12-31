package com.htec.city_management.common.constants;

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
     * Defines "having cities" relation.
     */
    public static final String HAVING_CITIES_REL_NAME = "havingCities";

    /**
     * Defines "of country' relation.
     */
    public static final String OF_COUNTRY_REL_NAME = "ofCountry";
}

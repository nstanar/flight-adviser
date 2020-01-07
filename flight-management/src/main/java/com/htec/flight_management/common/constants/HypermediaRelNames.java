package com.htec.flight_management.common.constants;

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
    public static final String HAVING_CITIES = "havingCities";

    /**
     * Defines "for city" relation.
     */
    public static final String FOR_CITY = "forCity";

    /**
     * Defines "having comments" relation.
     */
    public static final String HAVING_COMMENTS = "havingComments";

    /**
     * Defines "having airports" relation.
     */
    public static final String HAVING_AIRPORTS = "havingAirports";
}

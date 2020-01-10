package com.htec.flight_management.service.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Nikola Stanar
 * <p>
 * Represents chepest flight request.
 */
@Getter
@Setter
@NoArgsConstructor
public class CheapestFlightRequestBody {

    /**
     * Link to the destination.
     */
    private String to;

    /**
     * Cost property.
     */
    @JsonProperty("cost_property")
    private String costProperty = "price";

    @JsonProperty("relationships")
    private Relationship relationship = new Relationship();

    /**
     * Algorithm to be used.
     */
    private String algorithm = "dijkstra";

    /**
     * Represents relationship for the request.
     */
    @Getter
    @Setter
    @NoArgsConstructor
    private static class Relationship {

        /**
         * Type of the relationship.
         */
        private final String type = "FLIES_TO";

        /**
         * Direction;
         */
        private String direction = "out";
    }
}

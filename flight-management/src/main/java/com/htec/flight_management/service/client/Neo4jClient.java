package com.htec.flight_management.service.client;

import com.htec.flight_management.service.client.dto.CheapestRouteDto;
import com.htec.flight_management.service.client.response.RelationshipResponse;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Nikola Stanar
 * <p>
 * Neo4j client.
 */
@Validated
public interface Neo4jClient {

    /**
     * Find cheapest path between source and destination airports.
     *
     * @param sourceAirportId      Source airport id.
     * @param destinationAirportId Destination airport id.
     * @return List of routes for cheapest path.
     */
    List<CheapestRouteDto> findCheapestPathBetween(@NotNull final Long sourceAirportId, @NotNull final Long destinationAirportId);

    /**
     * Finds relationship by id.
     *
     * @param relationshipLink Link to the relationship.
     * @return Relationship response.
     */
    RelationshipResponse getRelationship(@NotNull final String relationshipLink);

}

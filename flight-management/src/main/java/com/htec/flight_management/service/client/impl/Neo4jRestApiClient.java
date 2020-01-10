package com.htec.flight_management.service.client.impl;

import com.htec.flight_management.common.properties.Neo4jProperties;
import com.htec.flight_management.service.client.Neo4jClient;
import com.htec.flight_management.service.client.dto.CheapestRouteDto;
import com.htec.flight_management.service.client.request.CheapestFlightRequestBody;
import com.htec.flight_management.service.client.request.CheapestFlightResponseBody;
import com.htec.flight_management.service.client.response.RelationshipResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikola Stanar
 * <p>
 * Neo4j rest api client.
 * @see Neo4jClient
 */
@Component
@Slf4j
@AllArgsConstructor
public class Neo4jRestApiClient implements Neo4jClient {

    /**
     * Rest operations.
     */
    private final RestOperations restOperations;

    /**
     * Neo4j properties.
     */
    private final Neo4jProperties neo4jProperties;

    /**
     * Find cheapest path between source and destination airports.
     *
     * @param sourceAirportId      Source airport id.
     * @param destinationAirportId Destination airport id.
     * @return List of routes for cheapest path. Returns empty if not found.
     * @see Neo4jClient#findCheapestPathBetween(Long, Long)
     */
    @Override
    public List<CheapestRouteDto> findCheapestPathBetween(final @NotNull Long sourceAirportId, final @NotNull Long destinationAirportId) {
        final List<CheapestRouteDto> cheapestFlight = new ArrayList<>();

        CheapestFlightResponseBody cheapestFlightResponseBody = null;
        try {
            final CheapestFlightRequestBody cheapestFlightRequestBody = new CheapestFlightRequestBody();
            final String to = UriComponentsBuilder
                    .fromUriString(neo4jProperties.getNodeTemplate())
                    .buildAndExpand(destinationAirportId)
                    .toString();
            cheapestFlightRequestBody.setTo(to);
            cheapestFlightResponseBody = restOperations.exchange(
                    neo4jProperties.getPathTemplate(),
                    HttpMethod.POST,
                    new HttpEntity<>(cheapestFlightRequestBody),
                    CheapestFlightResponseBody.class,
                    sourceAirportId)
                    .getBody();
        } catch (final RestClientResponseException ex) {
            log.info("Neo4j API responded with status code {} due to: {}", ex.getRawStatusCode(), ex.getResponseBodyAsString());
        }

        if (cheapestFlightResponseBody != null) {
            cheapestFlightResponseBody.getRelationships().forEach(s -> {
                final RelationshipResponse relationship = getRelationship(s);
                final String start = relationship.getStart();
                final String end = relationship.getEnd();
                final Double price = (Double) relationship.getData().get("price");
                final String airlineCode = (String) relationship.getData().get("airlineCode");
                final Integer stops = (Integer) relationship.getData().get("stops");
                final CheapestRouteDto cheapestRouteDto = CheapestRouteDto.builder()
                        .sourceAirportId(Long.parseLong(start.substring(start.lastIndexOf('/') + 1)))
                        .destinationAirportId(Long.parseLong(end.substring(end.lastIndexOf('/') + 1)))
                        .price(BigDecimal.valueOf(price))
                        .airlineCode(airlineCode)
                        .stops(stops)
                        .build();

                cheapestFlight.add(cheapestRouteDto);
            });
        }

        return cheapestFlight;
    }


    /**
     * Finds relationship by id.
     *
     * @param relationshipLink Link to the relationship.
     * @return Relationship response.
     * @see Neo4jClient#getRelationship(String)
     */
    @Override
    public RelationshipResponse getRelationship(final @NotNull String relationshipLink) {
        return restOperations.getForObject(relationshipLink, RelationshipResponse.class);
    }

}

package com.htec.flight_management.repository.entity;

import com.htec.domain_starter.repository.entity.neo4j.Neo4jBaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * @author Nikola Stanar
 * <p>
 * Entity class representing flight.
 */
@Getter
@Setter
@NoArgsConstructor
@RelationshipEntity("FLIES_TO")
public class Flight extends Neo4jBaseEntity {

    /**
     * Source airport.
     */
    @StartNode
    private Airport source;

    /**
     * Destination airport.
     */
    @EndNode
    private Airport destination;

    /**
     * 2-letter (IATA) or 3-letter (ICAO) code of the airline.
     */
    @Index
    private String airlineCode;

    /**
     * Number of stops.
     * 0 for direct flight.
     */
    private int stops;

    /**
     * Price.
     * <p>
     * Neo4j does not support {@link java.math.BigDecimal}.
     */
    private Double price;
}

package com.htec.flight_management.repository.entity;

import com.htec.domain_starter.repository.entity.neo4j.Neo4jBaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.math.BigDecimal;

import static org.neo4j.ogm.annotation.Relationship.INCOMING;

/**
 * @author Nikola Stanar
 * <p>
 * Entity class representing flight.
 */
@Getter
@Setter
@NoArgsConstructor
@NodeEntity
public class Flight extends Neo4jBaseEntity<Long> {

    /**
     * Source airport.
     */
    @Relationship(type = "SOURCE")
    private Airport source;

    /**
     * Destination airport.
     */
    @Relationship(type = "DESTINATION")
    private Airport destination;

    /**
     * Airline that hosts the flight.
     */
    @Relationship(type = "HOSTS", direction = INCOMING)
    private Airline airline;

    /**
     * Number of stops.
     * 0 for direct flight.
     */
    private int stops;

    /**
     * Price.
     */
    private BigDecimal price;

}

package com.htec.flight_management.repository.entity;

import com.htec.domain_starter.repository.entity.neo4j.Neo4jBaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Nikola Stanar
 * <p>
 * Entity class representing airline.
 */
@Getter
@Setter
@NoArgsConstructor
@NodeEntity
public class Airline extends Neo4jBaseEntity<Long> {

    /**
     * 2-letter (IATA) or 3-letter (ICAO) code of the airline.
     */
    private String name;

    /**
     * Flights hosted by airline.
     */
    @Relationship(type = "HOSTS")
    private Set<Flight> flights = new HashSet<>();
}

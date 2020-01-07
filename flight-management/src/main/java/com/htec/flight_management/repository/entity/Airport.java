package com.htec.flight_management.repository.entity;

import com.htec.domain_starter.repository.entity.neo4j.Neo4jBaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

import static org.neo4j.ogm.annotation.Relationship.INCOMING;

/**
 * @author Nikola Stanar
 * <p>
 * Entity class representing airport.
 * <p>
 * Composite indexes and required fields are supported only in Neo4 Enterprise!
 */
@Getter
@Setter
@NoArgsConstructor
@NodeEntity
public class Airport extends Neo4jBaseEntity {

    /**
     * Name.
     */
    @Index
    private String name;

    /**
     * 3 letter or 4 letter code.
     */
    @Index(unique = true)
    private String code;

    /**
     * City airport belongs to.
     */
    @Relationship(type = "HAS_AIRPORT", direction = INCOMING)
    private City city;

    /**
     * Flights airport have.
     */
    @Relationship(value = "HAS_FLIGHT")
    private Set<Flight> flights;

}

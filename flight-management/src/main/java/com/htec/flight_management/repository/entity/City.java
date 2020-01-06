package com.htec.flight_management.repository.entity;

import com.htec.domain_starter.repository.entity.neo4j.Neo4jBaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

import static org.neo4j.ogm.annotation.Relationship.INCOMING;


/**
 * @author Nikola Stanar
 * <p>
 * Entity class representing city.
 */
@NodeEntity
@NoArgsConstructor
@Getter
@Setter
public class City extends Neo4jBaseEntity {

    /**
     * Name of the city.
     */
    private String name;

    /**
     * Country id.
     * Redundancy needed here because of index.
     */
    private Long countryId;

    /**
     * Country in which city resides in.
     */
    @Relationship(type = "HAS_CITY", direction = INCOMING)
    private Country country;

    /**
     * Description of the city.
     */
    private String description;

    /**
     * Comments for the city.
     */
    @Relationship(type = "HAS_COMMENT")
    private final Set<Comment> comments = new HashSet<>();
}

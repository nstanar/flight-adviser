package com.htec.flight_management.repository.entity;

import com.htec.domain_starter.repository.entity.neo4j.Neo4jBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Nikola Stanar
 * <p>
 * Entity class representing country.
 */
@NodeEntity
@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = "cities")
public class Country extends Neo4jBaseEntity<Long> {

    /**
     * Country name.
     */
    @Index(unique = true)
    private String name;

    /**
     * Collection of cities residing in country.
     */
    @Relationship(type = "HAS_CITY")
    private Set<City> cities = new HashSet<>();

}

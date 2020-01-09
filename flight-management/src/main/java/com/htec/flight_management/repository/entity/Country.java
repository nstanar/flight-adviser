package com.htec.flight_management.repository.entity;

import com.htec.domain_starter.repository.entity.neo4j.Neo4jBaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
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
public class Country extends Neo4jBaseEntity {

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

    /**
     * Setter for name.
     *
     * @param name Name.
     */
    public void setName(final String name) {
        this.name = StringUtils.strip(name);
    }

    /**
     * Checks if countries are equal to one another.
     *
     * @param o Object to be compared.
     * @return True if equal.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Country country = (Country) o;

        return new EqualsBuilder()
                .append(name.toUpperCase(), country.getName().toUpperCase())
                .isEquals();
    }

    /**
     * Calculates hash code for country.
     *
     * @return Hash code.
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name.toUpperCase())
                .toHashCode();
    }

}

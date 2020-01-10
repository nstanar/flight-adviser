package com.htec.flight_management.repository.entity;

import com.htec.domain_starter.repository.entity.neo4j.Neo4jBaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.neo4j.ogm.annotation.Index;
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
     * Country in which city resides in.
     */
    @Relationship(type = "HAS_CITY", direction = INCOMING)
    private Country country;

    /**
     * Composite indexes and required fields are supported only in Neo4 Enterprise!
     * Thus defining uniqueness this way.
     */
    @Index(unique = true)
    @Setter(AccessLevel.PRIVATE)
    private String cityNameCountryName;

    /**
     * Description of the city.
     */
    private String description;

    /**
     * Comments for the city.
     */
    @Relationship(type = "HAS_COMMENT")
    private final Set<Comment> comments = new HashSet<>();

    /**
     * Airports belonging to city.
     */
    @Relationship(type = "HAS_AIRPORT")
    private final Set<Airport> airports = new HashSet<>();

    /**
     * Setter for composite unique constraint.
     */
    private void setCityNameCountryName() {
        this.cityNameCountryName = StringUtils.strip(name + country.getName()).toUpperCase();
    }

    /**
     * Setter for country.
     *
     * @param country Country.
     */
    public void setCountry(final Country country) {
        this.country = country;
        if (name != null) {
            setCityNameCountryName();
        }
    }

    /**
     * Setter for name.
     *
     * @param name Name.
     */
    public void setName(final String name) {
        this.name = StringUtils.strip(name);
        if (country != null) {
            setCityNameCountryName();
        }
    }

    /**
     * Checks if cities are equal to one another.
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

        final City city = (City) o;

        return new EqualsBuilder()
                .append(cityNameCountryName, city.getCityNameCountryName())
                .isEquals();
    }

    /**
     * Calculates hash code for city.
     *
     * @return Hash code.
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(cityNameCountryName)
                .toHashCode();
    }

}

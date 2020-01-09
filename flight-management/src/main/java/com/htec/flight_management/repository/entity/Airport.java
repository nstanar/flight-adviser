package com.htec.flight_management.repository.entity;

import com.htec.domain_starter.repository.entity.neo4j.Neo4jBaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Objects;

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
     * 3 letter IATA code.
     * Null if not assigned.
     */
    @Index(unique = true)
    private String iataCode;

    /**
     * 4 letter ICAO code.
     * Null if not assigned.
     */
    @Index(unique = true)
    private String icaoCode;

    /**
     * Latitude.
     */
    private double latitude;

    /**
     * Longitude.
     */
    private double longitude;

    /**
     * Outgoing flight.
     */
    @Relationship(value = "FLIES_TO")
    private Flight out;

    /**
     * Incoming flight.
     */
    @Relationship(value = "FLIES_TO", direction = INCOMING)
    private Flight inc;

    /**
     * City airport belongs to.
     */
    @Relationship(type = "HAS_AIRPORT", direction = INCOMING)
    private City city;

    /**
     * Setter for name.
     *
     * @param name Name.
     */
    public void setName(final String name) {
        this.name = StringUtils.strip(name);
    }

    /**
     * Setter for IATA code.
     *
     * @param iataCode 3-letter unique iata code.
     */
    public void setIataCode(final String iataCode) {
        this.iataCode = StringUtils.deleteWhitespace(iataCode).toUpperCase();
    }

    /**
     * Setter for ICAO code.
     *
     * @param icaoCode 4-letter unique iata code.
     */
    public void setIcaoCode(final String icaoCode) {
        this.icaoCode = StringUtils.deleteWhitespace(icaoCode).toUpperCase();
    }

    /**
     * Setter for IATA or ICAO code.
     *
     * @param code 3-letter or 4-letter code.
     */
    public void setCode(final String code) {
        final String dandyCode = StringUtils.deleteWhitespace(code).toUpperCase();
        if (code.length() == 3) {
            setIataCode(dandyCode);
        } else {
            setIcaoCode(dandyCode);
        }
    }

    /**
     * Checks if airports are equal to one another.
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

        final Airport airport = (Airport) o;

        return Objects.equals(iataCode, airport.getIataCode()) &&
                Objects.equals(icaoCode, airport.getIcaoCode());
    }

    /**
     * Calculates hash code for airport.
     *
     * @return Hash code.
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(iataCode)
                .append(icaoCode)
                .toHashCode();
    }
}

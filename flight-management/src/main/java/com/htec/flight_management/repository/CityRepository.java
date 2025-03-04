package com.htec.flight_management.repository;

import com.htec.domain_starter.repository.SearchableRepository;
import com.htec.flight_management.repository.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Nikola Stanar
 * <p>
 * Repository for {@link City}.
 */
public interface CityRepository extends SearchableRepository<City> {

    /**
     * Finds page of cities by country id.
     *
     * @param countryId Country id.
     * @param pageable  Check {@link Pageable}.
     * @return Page of cities.
     */
    @Transactional(readOnly = true)
    @Query(value = "MATCH (city:City) MATCH (country:Country) " +
            "WHERE id(country) = {0} " +
            "MATCH (city)<-[:HAS_CITY]-(country) " +
            "WITH DISTINCT city " +
            "RETURN city",
            countQuery = "MATCH (city:City) MATCH (country:Country) " +
                    "WHERE id(country) = {0} " +
                    "MATCH (city)<-[:HAS_CITY]-(country) " +
                    "WITH DISTINCT city " +
                    "RETURN COUNT(city)"
    )
    Page<City> findAllByCountryId(final Long countryId, final Pageable pageable);

    /**
     * Finds city with given name in certain country.
     *
     * @param name City name.
     * @return List of cities.
     */
    @Transactional(readOnly = true)
    List<City> findByNameIgnoreCase(final String name);

}

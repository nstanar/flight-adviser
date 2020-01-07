package com.htec.flight_management.repository;

import com.htec.domain_starter.repository.SearchableRepository;
import com.htec.flight_management.repository.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Nikola Stanar
 * <p>
 * Repository for {@link City}.
 */
public interface CityRepository extends SearchableRepository<City, Long> {

    /**
     * Finds page of cities by country id.
     *
     * @param countryId Country id.
     * @param pageable  Check {@link Pageable}.
     * @return Page of cities.
     */
    @Transactional(readOnly = true)
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

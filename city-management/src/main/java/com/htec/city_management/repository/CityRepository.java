package com.htec.city_management.repository;

import com.htec.city_management.repository.entity.City;
import com.htec.domain_starter.repository.SearchableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

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
    Page<City> findAllByCountryId(final Long countryId, final Pageable pageable);

    /**
     * Checks if city with given name in certain country already exists.
     *
     * @param name      City name.
     * @param countryId Id of the country.
     * @return True if exists; otherwise false.
     */
    @Transactional(readOnly = true)
    boolean existsByNameIgnoreCaseAndCountryId(final String name, final Long countryId);

}

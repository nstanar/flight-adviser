package com.htec.flight_management.repository;

import com.htec.flight_management.repository.entity.Country;
import com.htec.domain_starter.repository.SearchableRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Nikola Stanar
 * <p>
 * Repository for {@link Country}.
 */
public interface CountryRepository extends SearchableRepository<Country> {

    /**
     * Finds country with given name.
     *
     * @param name Country name.
     * @return Optional country.
     */
    @Transactional(readOnly = true)
    Optional<Country> findByNameIgnoreCase(final String name);

}

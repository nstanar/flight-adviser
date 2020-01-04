package com.htec.city_management.repository;

import com.htec.city_management.repository.entity.Country;
import com.htec.domain_starter.repository.SearchableRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Nikola Stanar
 * <p>
 * Repository for {@link Country}.
 */
public interface CountryRepository extends SearchableRepository<Country> {

    /**
     * Checks if country with given name already exists (ignores case).
     *
     * @param name Country name.
     * @return True if exists; otherwise false.
     */
    @Transactional(readOnly = true)
    boolean existsByNameIgnoreCase(final String name);

}

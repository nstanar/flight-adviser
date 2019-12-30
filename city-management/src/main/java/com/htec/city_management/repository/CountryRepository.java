package com.htec.city_management.repository;

import com.htec.city_management.repository.entity.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Nikola Stanar
 * <p>
 * Repository for {@link Country}.
 */
//TODO: add bulk insert
public interface CountryRepository extends JpaRepository<Country, Long> {

    /**
     * Finds page of countries matching name filter.
     *
     * @param nameFilter Name filter.
     * @return Page of countries matching name filter.
     */
    @Transactional(readOnly = true)
    @Query("SELECT c from Country c " +
            "WHERE :name filter IS NULL OR c.name LIKE :nameFilter")
    Page<Country> findAllBy(@Param("nameFilter") final String nameFilter, final Pageable pageable);
}

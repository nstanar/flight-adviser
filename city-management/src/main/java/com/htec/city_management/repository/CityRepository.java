package com.htec.city_management.repository;

import com.htec.city_management.repository.entity.City;
import com.htec.domain_starter.repository.SearchableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Nikola Stanar
 * <p>
 * Repository for {@link City}.
 */
public interface CityRepository extends SearchableRepository<City> {

    @Transactional(readOnly = true)
    @Query("SELECT city FROM City city " +
            "INNER JOIN Country country ON city.country.id=country.id " +
            "WHERE country.id=:countryId")
    Page<City> findAllBy(@Param("countryId") final Long countryId, final Pageable pageable);

}

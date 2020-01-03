package com.htec.city_management.service;

import com.htec.city_management.repository.entity.Country;
import com.htec.city_management.service.dto.CityDto;
import com.htec.city_management.service.dto.CountryDto;
import com.htec.domain_starter.service.SearchableService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * @author Nikola Stanar
 * <p>
 * Service for operations over country.
 */
@Transactional
@Validated
public interface CountryService extends SearchableService<CountryDto, Country> {

    /**
     * Finds page of cities belonging to country.
     *
     * @param countryId Country id.
     * @param pageable  Check pageable.
     * @return Page of cities belonging to country;
     */
    @Transactional(readOnly = true)
    Page<CityDto> findBy(@NotNull final Long countryId, @NotNull final Pageable pageable);

    /**
     * Adds city to the country of certain id.
     *
     * @param countryId Id of the country.
     * @param city      City to be added.
     * @return Optional city created if country exists.
     */
    Optional<CityDto> createAndAssignFrom(@NotNull final Long countryId, @NotNull @Valid final CityDto city);

}

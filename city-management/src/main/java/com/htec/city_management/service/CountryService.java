package com.htec.city_management.service;

import com.htec.city_management.service.dto.CityDto;
import com.htec.city_management.service.dto.CountryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Optional;

/**
 * @author Nikola Stanar
 * <p>
 * Service for operations over country.
 */
public interface CountryService {

    /**
     * Finds optional country.
     *
     * @param id id of the country.
     * @return Optional country.
     */
    Optional<CountryDto> findBy(final Long id);

    /**
     * Creates country from dto.
     *
     * @param country Dto holding content that is about to be created.
     * @return Id of the created country.
     */
    Long create(@NotNull @Valid final CountryDto country);

    /**
     * Creates countries from dtoS.
     *
     * @param countries DtoS holding content that is about to be created.
     */
    void create(@NotEmpty final Collection<@NotNull @Valid CountryDto> countries);

    /**
     * Finds page of countries matching name prefix.
     * If empty prefix is ignored.
     *
     * @param namePrefix Name prefix.
     * @param pageable   Check {@link Pageable}.
     * @return Page of countries matching name prefix.
     */
    Page<CountryDto> findBy(final String namePrefix, @NotNull final Pageable pageable);

    /**
     * Finds page of cities belonging to country.
     *
     * @param countryId Country id.
     * @param pageable  Check pageable.
     * @return Page of cities belonging to country;
     */
    Page<CityDto> findAllBy(Long countryId, Pageable pageable);

    /**
     * Adds city to the country of certain id.
     *
     * @param countryId Id of the country.
     * @param city      City to be added.
     * @return Optional city created if country exists.
     */
    Optional<CityDto> addOwningAssociationBetween(@NotNull final Long countryId, @NotNull @Valid final CityDto city);

}

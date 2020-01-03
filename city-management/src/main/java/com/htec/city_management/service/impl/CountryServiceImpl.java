package com.htec.city_management.service.impl;

import com.htec.city_management.repository.CityRepository;
import com.htec.city_management.repository.CountryRepository;
import com.htec.city_management.repository.entity.City;
import com.htec.city_management.repository.entity.Country;
import com.htec.city_management.service.CountryService;
import com.htec.city_management.service.dto.CityDto;
import com.htec.city_management.service.dto.CountryDto;
import com.htec.city_management.service.dto.converter.CityDtoConverter;
import com.htec.city_management.service.dto.converter.CountryDtoConverter;
import com.htec.domain_starter.repository.SearchableRepository;
import com.htec.domain_starter.service.dto.converter.DtoConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * @author Nikola Stanar
 * <p>
 * Service for operations over country.
 * @see CountryService
 */
@Service
@Transactional
@Validated
@Slf4j
@AllArgsConstructor
//TODO: if there is time, introduce AOP for cross-cutting concerns like logging.
public class CountryServiceImpl implements CountryService {

    /**
     * Repository for country.
     */
    private final CountryRepository countryRepository;

    /**
     * Dto converter for country.
     */
    private final CountryDtoConverter countryDtoConverter;

    /**
     * Repository for city.
     */
    private final CityRepository cityRepository;

    /**
     * Dto converter for city.
     */
    private final CityDtoConverter cityDtoConverter;

    /**
     * Finds page of cities belonging to country.
     *
     * @param countryId Country id.
     * @param pageable  Check pageable.
     * @return Page of cities belonging to country;
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CityDto> findBy(@NotNull final Long countryId, @NotNull final Pageable pageable) {
        log.info("Fetching {} of cities for country of id {}.", pageable, countryId);
        return cityRepository
                .findAllByCountryId(countryId, pageable)
                .map(cityDtoConverter::from);
    }

    /**
     * Adds city to country of certain id.
     *
     * @param countryId Id of the country.
     * @param city      City to be added.
     * @return Optional city created if country exists.
     * @see CountryService#createAndAssignFrom(Long, CityDto)
     */
    @Override
    public Optional<CityDto> createAndAssignFrom(final @NotNull Long countryId, final @NotNull @Valid CityDto city) {
        log.info("Adding city {} to country of id {}.", city, countryId);
        return countryRepository.findById(countryId)
                .map(country -> {
                    final City cityToBeCreated = cityDtoConverter.from(city);
                    cityToBeCreated.setCountry(country);
                    final City createdCity = cityRepository.save(cityToBeCreated);
                    log.info("City successfully added to country and given id {}.", createdCity.getId());
                    return createdCity;
                })
                .map(cityDtoConverter::from);
    }

    /**
     * Gets dto converter.
     *
     * @return Check {@link DtoConverter}.
     * @see CountryService#getUserDtoConverter()
     */
    @Override
    public DtoConverter<CountryDto, Country> getUserDtoConverter() {
        return countryDtoConverter;
    }

    /**
     * Gets searchable repository.
     *
     * @return Check {@link SearchableRepository}.
     * @see CountryService#getUserRepository()
     */
    @Override
    public SearchableRepository<Country> getUserRepository() {
        return countryRepository;
    }
}

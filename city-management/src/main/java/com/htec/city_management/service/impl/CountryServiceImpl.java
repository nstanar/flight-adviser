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
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
     * Finds optional country by id.
     *
     * @param id id of the country.
     * @return Optional country.
     * @see CountryService#findBy(Long)
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CountryDto> findBy(final Long id) {
        return countryRepository
                .findById(id)
                .map(countryDtoConverter::from);
    }

    /**
     * Creates country from dto.
     *
     * @param country Dto holding content that is about to be created.
     * @return Id of the created country.
     * @see CountryService#create(CountryDto)
     */
    @Override
    public Long create(final @NotNull @Valid CountryDto country) {
        log.info("Creating country :{}", country);
        final Country countryEntity = countryDtoConverter.from(country);
        final Country createdEntity = countryRepository.save(countryEntity);
        final Long id = createdEntity.getId();
        log.info("Country successfully created and given id {}.", id);
        return id;
    }

    /**
     * Creates countries for collection of dtoS.
     *
     * @param countries DtoS holding content that is about to be created.
     * @see CountryService#create(Collection)
     */
    @Override
    public void create(final @NotEmpty Collection<@NotNull @Valid CountryDto> countries) {
        //TODO: add bulk insert
        final long startTime = System.currentTimeMillis();
        final int size = countries.size();
        log.info("Creating collection of {} countries.", size);
        final Set<Country> countriesToBeCreated = countries
                .stream()
                .map(countryDtoConverter::from)
                .collect(Collectors.toSet());
        countryRepository.saveAll(countriesToBeCreated);
        final long endTime = System.currentTimeMillis();
        log.info("{} countries successfully created after {} ms.", size, endTime - startTime);
    }

    /**
     * Finds page of countries matching name prefix.
     * If empty prefix is ignored.
     *
     * @param namePrefix Name prefix.
     * @param pageable   Check {@link Pageable}.
     * @return Page of countries.
     * @see CountryService#findWhereNameStartsWith(String, Pageable)
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CountryDto> findWhereNameStartsWith(final String namePrefix, final @NotNull Pageable pageable) {
        //TODO: TEST sending empty prefix
        log.info("Fetching page {} of countries matching name prefix {}", pageable, namePrefix);
        final String nameFilter = namePrefix + "%";
        return countryRepository
                .findAllBy(nameFilter, pageable)
                .map(countryDtoConverter::from);
    }

    /**
     * Finds page of cities belonging to country.
     *
     * @param countryId Country id.
     * @param pageable  Check pageable.
     * @return Page of cities belonging to country;
     */
    public Page<CityDto> findAllBy(final Long countryId, final Pageable pageable) {
        return cityRepository
                .findAllBy(countryId, pageable)
                .map(cityDtoConverter::from);
    }

    /**
     * Adds city to country of certain id.
     *
     * @param countryId Id of the country.
     * @param city      City to be added.
     * @return Optional city created if country exists.
     * @see CountryService#addOwningAssociationBetween(Long, CityDto)
     */
    @Override
    public Optional<CityDto> addOwningAssociationBetween(final @NotNull Long countryId, final @NotNull @Valid CityDto city) {
        Optional<CityDto> optionalCity = Optional.empty();
        log.info("Adding city {} to country of id {}.", city, countryId);
        final Optional<Country> optionalCountry = countryRepository.findById(countryId);
        if (optionalCountry.isPresent()) {
            final Country country = optionalCountry.get();
            final City cityToBeCreated = cityDtoConverter.from(city);
            cityToBeCreated.setCountry(country);
            final City createdCity = cityRepository.save(cityToBeCreated);
            log.info("City successfully added to country and given id {}.", createdCity.getId());
            optionalCity = Optional.of(cityDtoConverter.from(createdCity));
        } else {
            log.info("Country does not exist, thus city cannot be added.");
        }
        return optionalCity;
    }
}

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
import com.htec.domain_starter.service.validation.chain.BusinessValidatorChain;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
     * Business validator chain for country.
     */
    private final BusinessValidatorChain<CountryDto> businessValidatorChain;

    /**
     * Repository for city.
     */
    private final CityRepository cityRepository;

    /**
     * Dto converter for city.
     */
    private final CityDtoConverter cityDtoConverter;

    /**
     * Message source.
     */
    private final MessageSource messageSource;

    /**
     * Finds page of cities belonging to country.
     *
     * @param countryId Country id.
     * @param pageable  Check pageable.
     * @return Page of cities belonging to country;
     */
    @Override
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
        city.setCountryId(countryId);
        //TODO: validate here
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
     * @see CountryService#getDtoConverter()
     */
    @Override
    public DtoConverter<CountryDto, Country> getDtoConverter() {
        return countryDtoConverter;
    }

    /**
     * Gets message source.
     *
     * @return Message source.
     * @see CountryService#getMessageSource()
     */
    @Override
    public MessageSource getMessageSource() {
        return messageSource;
    }

    /**
     * Gets business validator chain.
     *
     * @return Business validator chain.
     */
    @Override
    public Optional<BusinessValidatorChain<CountryDto>> getBusinessValidatorChain() {
        return Optional.ofNullable(businessValidatorChain);
    }

    /**
     * Gets searchable repository.
     *
     * @return Check {@link SearchableRepository}.
     * @see CountryService#getRepository()
     */
    @Override
    public SearchableRepository<Country> getRepository() {
        return countryRepository;
    }
}

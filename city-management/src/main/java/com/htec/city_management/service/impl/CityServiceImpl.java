package com.htec.city_management.service.impl;

import com.htec.city_management.repository.CityRepository;
import com.htec.city_management.repository.entity.City;
import com.htec.city_management.service.CityService;
import com.htec.city_management.service.dto.CityDto;
import com.htec.city_management.service.dto.converter.CityDtoConverter;
import com.htec.domain_starter.repository.SearchableRepository;
import com.htec.domain_starter.service.dto.converter.DtoConverter;
import com.htec.domain_starter.service.validation.chain.BusinessValidatorChain;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * @author Nikola Stanar
 * <p>
 * Service exposing operations over city.
 * Implementation of {@link CityService}.
 */
@Service
@Slf4j
@AllArgsConstructor
public class CityServiceImpl implements CityService {

    /**
     * Jpa repository for city.
     */
    private final CityRepository repository;

    /**
     * Dto converter for city.
     */
    private final CityDtoConverter dtoConverter;

    /**
     * Business validator chain for city.
     */
    private final BusinessValidatorChain<CityDto> businessValidatorChain;

    /**
     * Message source.
     */
    private final MessageSource messageSource;

    /**
     * Finds page of cities belonging to country of id.
     *
     * @param countryId Id of the country.
     * @param pageable  Check {@link Pageable}.
     * @return Page of cities.
     */
    @Override
    public Page<CityDto> findAllByCountryId(final @NotNull Long countryId, final @NotNull Pageable pageable) {
        log.info("Fetching {} of cities for country of id {}.", pageable, countryId);
        return repository
                .findAllByCountryId(countryId, pageable)
                .map(dtoConverter::from);
    }

    /**
     * Gets dto converter.
     *
     * @return Check {@link DtoConverter}.
     * @see CityService#getDtoConverter()
     */
    @Override
    public DtoConverter<CityDto, City> getDtoConverter() {
        return dtoConverter;
    }

    /**
     * Gets business validator chain.
     *
     * @return Business validator chain.
     * @see CityService#getBusinessValidatorChain()
     */
    @Override
    public Optional<BusinessValidatorChain<CityDto>> getBusinessValidatorChain() {
        return Optional.ofNullable(businessValidatorChain);
    }

    /**
     * Gets message source.
     *
     * @return Message source.
     * @see CityService#getMessageSource()
     */
    @Override
    public MessageSource getMessageSource() {
        return messageSource;
    }

    /**
     * Gets searchable repository.
     *
     * @return Check {@link SearchableRepository}.
     * @see CityService#getRepository()
     */
    @Override
    public SearchableRepository<City> getRepository() {
        return repository;
    }
}

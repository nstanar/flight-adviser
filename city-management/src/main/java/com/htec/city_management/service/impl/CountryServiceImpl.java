package com.htec.city_management.service.impl;

import com.htec.city_management.repository.CountryRepository;
import com.htec.city_management.repository.entity.Country;
import com.htec.city_management.service.CountryService;
import com.htec.city_management.service.dto.CountryDto;
import com.htec.city_management.service.dto.converter.CountryDtoConverter;
import com.htec.domain_starter.repository.SearchableRepository;
import com.htec.domain_starter.service.dto.converter.DtoConverter;
import com.htec.domain_starter.service.validation.chain.BusinessValidatorChain;
import com.htec.domain_starter.service.validation.util.ExceptionUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    private final CountryRepository repository;

    /**
     * Dto converter for country.
     */
    private final CountryDtoConverter dtoConverter;

    /**
     * Business validator chain for country.
     */
    private final BusinessValidatorChain<CountryDto> businessValidatorChain;

    /**
     * Exception util.
     */
    private final ExceptionUtil exceptionUtil;

    /**
     * Gets dto converter.
     *
     * @return Check {@link DtoConverter}.
     * @see CountryService#getDtoConverter()
     */
    @Override
    public DtoConverter<CountryDto, Country> getDtoConverter() {
        return dtoConverter;
    }

    /**
     * Gets exception util.
     *
     * @return Exception util.
     * @see CountryService#getExceptionUtil()
     */
    @Override
    public ExceptionUtil getExceptionUtil() {
        return exceptionUtil;
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
        return repository;
    }
}

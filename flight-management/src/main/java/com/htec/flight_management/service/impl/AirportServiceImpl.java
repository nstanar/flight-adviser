package com.htec.flight_management.service.impl;

import com.htec.domain_starter.repository.SearchableRepository;
import com.htec.domain_starter.service.dto.converter.DtoConverter;
import com.htec.domain_starter.service.validation.chain.BusinessValidatorChain;
import com.htec.domain_starter.service.validation.util.ExceptionUtil;
import com.htec.flight_management.repository.AirportRepository;
import com.htec.flight_management.repository.entity.Airport;
import com.htec.flight_management.service.AirportService;
import com.htec.flight_management.service.dto.AirportDto;
import com.htec.flight_management.service.dto.converter.AirportDtoConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * @author Nikola Stanar
 * <p>
 * Service exposing operations over airport.
 * @see AirportService
 */
@Service
@Slf4j
@AllArgsConstructor
public class AirportServiceImpl implements AirportService {

    /**
     * Business validator chain.
     */
    private final BusinessValidatorChain<AirportDto> businessValidatorChain;

    /**
     * Repository for airport.
     */
    private final AirportRepository repository;

    /**
     * Dto converter for airport.
     */
    private final AirportDtoConverter dtoConverter;

    /**
     * Exception util.
     */
    private final ExceptionUtil exceptionUtil;

    /**
     * Finds page of airports by city id.
     *
     * @param cityId   Id of the city.
     * @param pageable Check {@link Pageable}.
     * @return Page of airports.
     * @see AirportService#findByCityId(Long, Pageable)
     */
    @Override
    public Page<AirportDto> findByCityId(final @NotNull Long cityId, final @NotNull Pageable pageable) {
        log.info("Fetching {} of airports for city of id {}.", pageable, cityId);
        return repository
                .findAllByCityId(cityId, pageable)
                .map(dtoConverter::from);
    }

    /**
     * Gets business validator chain.
     *
     * @return Business validator chain.
     * @see AirportService#getBusinessValidatorChain()
     */
    @Override
    public Optional<BusinessValidatorChain<AirportDto>> getBusinessValidatorChain() {
        return Optional.ofNullable(businessValidatorChain);
    }

    /**
     * Gets repository.
     *
     * @return Repository.
     * @see AirportService#getRepository()
     */
    @Override
    public SearchableRepository<Airport> getRepository() {
        return repository;
    }

    /**
     * Gets exception util.
     *
     * @return Exception util.
     * @see AirportService#getExceptionUtil()
     */
    @Override
    public ExceptionUtil getExceptionUtil() {
        return exceptionUtil;
    }

    /**
     * Gets dto converter.
     *
     * @return Dto converter.
     * @see AirportService#getDtoConverter()
     */
    @Override
    public DtoConverter<AirportDto, Airport> getDtoConverter() {
        return dtoConverter;
    }
}

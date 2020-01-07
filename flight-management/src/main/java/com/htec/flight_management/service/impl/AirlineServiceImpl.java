package com.htec.flight_management.service.impl;

import com.htec.domain_starter.service.dto.converter.DtoConverter;
import com.htec.domain_starter.service.validation.chain.BusinessValidatorChain;
import com.htec.domain_starter.service.validation.util.ExceptionUtil;
import com.htec.flight_management.repository.AirlineRepository;
import com.htec.flight_management.repository.entity.Airline;
import com.htec.flight_management.service.AirlineService;
import com.htec.flight_management.service.dto.AirlineDto;
import com.htec.flight_management.service.dto.converter.AirlineDtoConverter;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Nikola Stanar
 * <p>
 * Service exposing operations over airline.
 * @see AirlineService
 */
@Service
@AllArgsConstructor
public class AirlineServiceImpl implements AirlineService {

    /**
     * Business validator chain for airline.
     */
    private final BusinessValidatorChain<AirlineDto, Long> businessValidatorChain;

    /**
     * Airline repository.
     */
    private final AirlineRepository airlineRepository;

    /**
     * Dto converter for airline.
     */
    private final AirlineDtoConverter airlineDtoConverter;

    /**
     * Exception util.
     */
    private final ExceptionUtil exceptionUtil;

    /**
     * Gets business validator chain.
     *
     * @return Business validator chain.
     * @see AirlineService#getBusinessValidatorChain()
     */
    @Override
    public Optional<BusinessValidatorChain<AirlineDto, Long>> getBusinessValidatorChain() {
        return Optional.ofNullable(businessValidatorChain);
    }

    /**
     * Gets repository.
     *
     * @return Repository.
     * @see AirlineService#getRepository()
     */
    @Override
    public PagingAndSortingRepository<Airline, Long> getRepository() {
        return airlineRepository;
    }

    /**
     * Gets exception util.
     *
     * @return Exception util.
     * @see AirlineService#getExceptionUtil()
     */
    @Override
    public ExceptionUtil getExceptionUtil() {
        return exceptionUtil;
    }

    /**
     * Gets dto converter.
     *
     * @return Dto converter.
     * @see AirlineService#getDtoConverter()
     */
    @Override
    public DtoConverter<AirlineDto, Airline, Long> getDtoConverter() {
        return airlineDtoConverter;
    }

}

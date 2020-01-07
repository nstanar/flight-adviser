package com.htec.flight_management.service.impl;

import com.htec.domain_starter.service.dto.converter.DtoConverter;
import com.htec.domain_starter.service.validation.chain.BusinessValidatorChain;
import com.htec.domain_starter.service.validation.util.ExceptionUtil;
import com.htec.flight_management.repository.FlightRepository;
import com.htec.flight_management.repository.entity.Flight;
import com.htec.flight_management.service.FlightService;
import com.htec.flight_management.service.dto.FlightDto;
import com.htec.flight_management.service.dto.converter.FlightDtoConverter;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Nikola Stanar
 * <p>
 * Service exposing operations over flight.
 * @see FlightService
 */
@Service
@AllArgsConstructor
public class FlightServiceImpl implements FlightService {

    /**
     * Flight repository.
     */
    private final FlightRepository repository;

    /**
     * Flight dto converter.
     */
    private final FlightDtoConverter dtoConverter;

    /**
     * Exception util.
     */
    private final ExceptionUtil exceptionUtil;

    /**
     * Gets business validator chain.
     *
     * @return Business validator chain.
     * @see FlightService#getBusinessValidatorChain()
     */
    @Override
    public Optional<BusinessValidatorChain<FlightDto, Long>> getBusinessValidatorChain() {
        return Optional.empty();
    }

    /**
     * Gets repository.
     *
     * @return Repository.
     * @see FlightService#getRepository()
     */
    @Override
    public PagingAndSortingRepository<Flight, Long> getRepository() {
        return repository;
    }

    /**
     * Gets exception util.
     *
     * @return Exception util.
     */
    @Override
    public ExceptionUtil getExceptionUtil() {
        return exceptionUtil;
    }

    /**
     * Gets dto converter.
     *
     * @return Dto converter.
     */
    @Override
    public DtoConverter<FlightDto, Flight, Long> getDtoConverter() {
        return dtoConverter;
    }

}

package com.htec.flight_management.service.validation.validator.impl;

import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.domain_starter.service.validation.util.ExceptionUtil;
import com.htec.flight_management.repository.AirlineRepository;
import com.htec.flight_management.repository.entity.Airline;
import com.htec.flight_management.service.dto.AirlineDto;
import com.htec.flight_management.service.validation.AirlineCodeUniquenessValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * @author Nikola Stanar
 * <p>
 * Validates that airline code is unique.
 * @see AirlineCodeUniquenessValidator
 */
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class AirlineCodeUniquenessValidatorImpl implements AirlineCodeUniquenessValidator {

    /**
     * Repository for airline.
     */
    private final AirlineRepository airlineRepository;

    /**
     * Exception util.
     */
    private final ExceptionUtil exceptionUtil;

    /**
     * Message source key.
     */
    public static final String AIRLINE_CODE_ALREADY_EXISTS = "airline_code_already_exists";

    /**
     * Validates that airline code is unique.
     *
     * @param dto DTO to be validated
     * @see AirlineCodeUniquenessValidator#validate(BaseDto)
     */
    @Override
    public void validate(final @NotNull AirlineDto dto) {
        final Long id = dto.getId();
        final String code = dto.getCode();
        final Optional<Airline> optionalAirline = airlineRepository.findByCodeIgnoreCase(code);
        optionalAirline.ifPresent(airline ->
                AirlineCodeUniquenessValidator
                        .super
                        .validate(id, airline, AIRLINE_CODE_ALREADY_EXISTS, new Object[]{code})
        );
    }

    /**
     * Gets exception util.
     *
     * @return Exception util.
     * @see AirlineCodeUniquenessValidator#getExceptionUtil()
     */
    @Override
    public ExceptionUtil getExceptionUtil() {
        return exceptionUtil;
    }
}

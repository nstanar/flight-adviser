package com.htec.flight_management.service.validation.validator.impl;

import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.domain_starter.service.validation.util.ExceptionUtil;
import com.htec.flight_management.repository.AirportRepository;
import com.htec.flight_management.repository.entity.Airport;
import com.htec.flight_management.service.dto.AirportDto;
import com.htec.flight_management.service.validation.AirportCodeUniquenessValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * @author Nikola Stanar
 * <p>
 * Validates that airport code is unique.
 * @see AirportCodeUniquenessValidator
 */
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class AirportCodeUniquenessValidatorImpl implements AirportCodeUniquenessValidator {

    /**
     * Repository for airport.
     */
    private final AirportRepository airportRepository;

    /**
     * Exception util.
     */
    private final ExceptionUtil exceptionUtil;

    /**
     * Message source key.
     */
    public static final String AIRPORT_CODE_ALREADY_EXISTS = "airport_code_already_exists";

    /**
     * Validates that airport code is unique.
     *
     * @param dto DTO to be validated
     * @see AirportCodeUniquenessValidator#validate(BaseDto)
     */
    @Override
    public void validate(final @NotNull AirportDto dto) {
        final Long id = dto.getId();
        final String code = dto.getCode();
        final Optional<Airport> optionalAirport = airportRepository.findByCodeIgnoreCase(code);
        optionalAirport.ifPresent(airport ->
                AirportCodeUniquenessValidator
                        .super
                        .validate(id, airport, AIRPORT_CODE_ALREADY_EXISTS, new Object[]{code})
        );
    }

    /**
     * Gets exception util.
     *
     * @return Exception util.
     * @see AirportCodeUniquenessValidator#getExceptionUtil()
     */
    @Override
    public ExceptionUtil getExceptionUtil() {
        return exceptionUtil;
    }
}

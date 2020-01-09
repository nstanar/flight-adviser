package com.htec.flight_management.service.validation.validator.impl;

import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.domain_starter.service.validation.util.ExceptionUtil;
import com.htec.flight_management.service.dto.FlightDto;
import com.htec.flight_management.service.validation.SourceAndDestinationValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @author Nikola Stanar
 * <p>
 * Validates source and destination are not the same.
 * @see SourceAndDestinationValidator
 */
@Component
@AllArgsConstructor
public class SourceAndDestinationValidatorImpl implements SourceAndDestinationValidator {

    /**
     * Exception util.
     */
    private final ExceptionUtil exceptionUtil;

    private static final String SOURCE_SAME_AS_DESTINATION = "source_same_as_destination";

    /**
     * Validates source and destination are not the same.
     *
     * @param dto DTO to be validated
     * @see SourceAndDestinationValidator#validate(BaseDto)
     */
    @Override
    public void validate(final @NotNull FlightDto dto) {
        final Long sourceAirportId = dto.getSourceAirportId();
        final Long destinationAirportId = dto.getDestinationAirportId();
        if (sourceAirportId.equals(destinationAirportId)) {
            throw exceptionUtil.createBusinessValidationExceptionFrom(SOURCE_SAME_AS_DESTINATION, new Object[]{});
        }

    }

}

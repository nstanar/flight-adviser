package com.htec.flight_management.service.validation;

import com.htec.domain_starter.service.validation.validator.BusinessValidator;
import com.htec.flight_management.service.dto.FlightDto;

/**
 * @author Nikola Stanar
 * <p>
 * Validates source and destination are not the same.
 */
public interface SourceAndDestinationValidator extends BusinessValidator<FlightDto> {

}

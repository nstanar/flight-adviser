package com.htec.flight_management.service.validation;

import com.htec.domain_starter.service.validation.util.UniquenessValidatorTemplate;
import com.htec.flight_management.repository.entity.Airline;
import com.htec.flight_management.service.dto.AirlineDto;

/**
 * @author Nikola Stanar
 * <p>
 * Validates that airline code is unique.
 */
public interface AirlineCodeUniquenessValidator extends UniquenessValidatorTemplate<AirlineDto, Airline, Long> {

}

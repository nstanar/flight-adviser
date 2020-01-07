package com.htec.flight_management.service.validation;

import com.htec.domain_starter.service.validation.util.UniquenessValidatorTemplate;
import com.htec.flight_management.repository.entity.Airport;
import com.htec.flight_management.service.dto.AirportDto;

/**
 * @author Nikola Stanar
 * <p>
 * Validates that airport code is unique.
 */
public interface AirportCodeUniquenessValidator extends UniquenessValidatorTemplate<AirportDto, Airport> {

}

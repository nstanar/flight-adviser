package com.htec.flight_management.service.validation;

import com.htec.domain_starter.service.validation.util.UniquenessValidatorTemplate;
import com.htec.flight_management.repository.entity.City;
import com.htec.flight_management.service.dto.CityDto;

/**
 * @author Nikola Stanar
 * <p>
 * Validates that city name is unique within country boundaries.
 */
public interface CityNameUniquenessValidator extends UniquenessValidatorTemplate<CityDto, City> {

}

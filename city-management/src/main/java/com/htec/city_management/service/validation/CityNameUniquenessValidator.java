package com.htec.city_management.service.validation;

import com.htec.city_management.service.dto.CityDto;
import com.htec.domain_starter.service.validation.validator.BusinessValidator;

/**
 * @author Nikola Stanar
 * <p>
 * Validates that city name is unique within country boundaries.
 */
@FunctionalInterface
public interface CityNameUniquenessValidator extends BusinessValidator<CityDto> {

}

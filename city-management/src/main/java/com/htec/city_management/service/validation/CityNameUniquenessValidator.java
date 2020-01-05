package com.htec.city_management.service.validation;

import com.htec.city_management.repository.entity.City;
import com.htec.city_management.service.dto.CityDto;
import com.htec.domain_starter.service.validation.util.UniquenessValidatorTemplate;

/**
 * @author Nikola Stanar
 * <p>
 * Validates that city name is unique within country boundaries.
 */
public interface CityNameUniquenessValidator extends UniquenessValidatorTemplate<CityDto, City> {

}

package com.htec.city_management.service.validation;

import com.htec.city_management.repository.entity.Country;
import com.htec.city_management.service.dto.CountryDto;
import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.domain_starter.service.validation.util.UniquenessValidatorTemplate;
import com.htec.domain_starter.service.validation.validator.BusinessValidator;

/**
 * @author Nikola Stanar
 * <p>
 * Validates that country name is unique.
 * @see BusinessValidator#validate(BaseDto)
 */
public interface CountryNameUniquenessValidator extends UniquenessValidatorTemplate<CountryDto, Country> {

}

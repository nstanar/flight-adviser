package com.htec.flight_management.service.validation;

import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.domain_starter.service.validation.util.UniquenessValidatorTemplate;
import com.htec.domain_starter.service.validation.validator.BusinessValidator;
import com.htec.flight_management.repository.entity.Country;
import com.htec.flight_management.service.dto.CountryDto;

/**
 * @author Nikola Stanar
 * <p>
 * Validates that country name is unique.
 * @see BusinessValidator#validate(BaseDto)
 */
public interface CountryNameUniquenessValidator extends UniquenessValidatorTemplate<CountryDto, Country, Long> {

}

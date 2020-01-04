package com.htec.city_management.service.validation.validator.impl;

import com.htec.city_management.repository.CityRepository;
import com.htec.city_management.service.dto.CityDto;
import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.domain_starter.service.validation.exception.BusinessValidationException;
import com.htec.domain_starter.service.validation.validator.BusinessValidator;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

/**
 * @author Nikola Stanar
 * <p>
 * Validates that city name is unique within country boundaries.
 */
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class CityNameUniquenessValidator implements BusinessValidator<CityDto> {

    /**
     * Jpa repository for city.
     */
    private final CityRepository cityRepository;

    /**
     * Message source.
     */
    private final MessageSource messageSource;

    /**
     * Message source key.
     */
    public static final String CITY_NAME_ALREADY_EXISTS = "city_name_already_exists";

    /**
     * Validates that city name is unique within country boundaries.
     *
     * @param dto DTO to be validated
     * @see BusinessValidator#validate(BaseDto)
     */
    @Override
    public void validate(@NotNull final CityDto dto) {
        final String cityName = dto.getName();
        final Long countryId = dto.getCountryId();
        final boolean alreadyExists = cityRepository.existsByNameIgnoreCaseAndCountryId(cityName, countryId);
        if (alreadyExists) {
            final String message = messageSource.getMessage(CITY_NAME_ALREADY_EXISTS, new Object[]{cityName, countryId}, getLocale());
            throw new BusinessValidationException(message);
        }
    }

}

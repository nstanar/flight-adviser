package com.htec.city_management.service.validation.validator.impl;

import com.htec.city_management.repository.CountryRepository;
import com.htec.city_management.service.dto.CountryDto;
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
 * Validates that country name is unique.
 * @see BusinessValidator#validate(BaseDto)
 */
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class CountryNameUniquenessValidator implements BusinessValidator<CountryDto> {

    /**
     * Jpa repository for country.
     */
    private final CountryRepository countryRepository;

    /**
     * Message source.
     */
    private final MessageSource messageSource;

    /**
     * Message source key.
     */
    public static final String COUNTRY_NAME_ALREADY_EXISTS = "country_name_already_exists";

    /**
     * Validates that country name is unique.
     *
     * @param dto DTO to be validated
     * @see BusinessValidator#validate(BaseDto)
     */
    @Override
    public void validate(final @NotNull CountryDto dto) {
        final String countryName = dto.getName();
        final boolean alreadyExists = countryRepository.existsByNameIgnoreCase(countryName);
        if (alreadyExists) {
            final String message = messageSource.getMessage(COUNTRY_NAME_ALREADY_EXISTS, new Object[]{countryName}, getLocale());
            throw new BusinessValidationException(message);
        }
    }

}

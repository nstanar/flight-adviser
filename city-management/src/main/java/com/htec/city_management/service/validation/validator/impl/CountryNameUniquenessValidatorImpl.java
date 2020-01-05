package com.htec.city_management.service.validation.validator.impl;

import com.htec.city_management.repository.CountryRepository;
import com.htec.city_management.repository.entity.Country;
import com.htec.city_management.service.dto.CountryDto;
import com.htec.city_management.service.validation.CountryNameUniquenessValidator;
import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.domain_starter.service.validation.exception.BusinessValidationException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

/**
 * @author Nikola Stanar
 * <p>
 * Validates that country name is unique.
 * @see CountryNameUniquenessValidator
 */
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class CountryNameUniquenessValidatorImpl implements CountryNameUniquenessValidator {

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
     * @see CountryNameUniquenessValidator#validate(BaseDto)
     */
    @Override
    public void validate(final @NotNull CountryDto dto) {
        final Long id = dto.getId();
        final String name = dto.getName();
        final Optional<Country> optionalCountry = countryRepository.findByNameIgnoreCase(name);

        boolean alreadyExists = false;
        if (optionalCountry.isPresent()) {
            final Country country = optionalCountry.get();
            if (id != null) {
                // Existing country.
                if (!country.getId().equals(id)) {
                    // Existing country with different id and same name.
                    alreadyExists = true;
                }
            } else {
                // Completely new country with existing name.
                alreadyExists = true;
            }
        }

        if (alreadyExists) {
            final String message = messageSource.getMessage(COUNTRY_NAME_ALREADY_EXISTS, new Object[]{name}, getLocale());
            throw new BusinessValidationException(message);
        }
    }

}

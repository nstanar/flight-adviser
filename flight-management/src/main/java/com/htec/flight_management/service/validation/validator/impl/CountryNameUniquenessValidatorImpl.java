package com.htec.flight_management.service.validation.validator.impl;

import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.domain_starter.service.validation.util.ExceptionUtil;
import com.htec.flight_management.repository.CountryRepository;
import com.htec.flight_management.repository.entity.Country;
import com.htec.flight_management.service.dto.CountryDto;
import com.htec.flight_management.service.validation.CountryNameUniquenessValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Optional;

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
     * Repository for country.
     */
    private final CountryRepository countryRepository;

    /**
     * Exception util.
     */
    private final ExceptionUtil exceptionUtil;

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
        optionalCountry.ifPresent(country ->
                CountryNameUniquenessValidator
                        .super
                        .validate(id, country, COUNTRY_NAME_ALREADY_EXISTS, new Object[]{name})
        );
    }

    /**
     * Gets exception util.
     *
     * @return Exception util.
     * @see CountryNameUniquenessValidator#getExceptionUtil()
     */
    @Override
    public ExceptionUtil getExceptionUtil() {
        return exceptionUtil;
    }

}

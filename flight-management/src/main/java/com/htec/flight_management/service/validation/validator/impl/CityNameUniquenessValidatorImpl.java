package com.htec.flight_management.service.validation.validator.impl;

import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.domain_starter.service.validation.util.ExceptionUtil;
import com.htec.flight_management.repository.CityRepository;
import com.htec.flight_management.repository.entity.City;
import com.htec.flight_management.service.dto.CityDto;
import com.htec.flight_management.service.validation.CityNameUniquenessValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * @author Nikola Stanar
 * <p>
 * Validates that city name is unique within country boundaries.
 * @see CityNameUniquenessValidator
 */
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class CityNameUniquenessValidatorImpl implements CityNameUniquenessValidator {

    /**
     * Repository for city.
     */
    private final CityRepository cityRepository;

    /**
     * Exception util.
     */
    private final ExceptionUtil exceptionUtil;

    /**
     * Message source key.
     */
    public static final String CITY_NAME_ALREADY_EXISTS = "city_name_already_exists";

    /**
     * Validates that city name is unique within country boundaries.
     *
     * @param dto DTO to be validated
     * @see CityNameUniquenessValidator#validate(BaseDto)
     */
    @Override
    public void validate(@NotNull final CityDto dto) {
        final Long id = dto.getId();
        final String name = dto.getName();
        final Long countryId = dto.getCountryId();
        final List<City> citiesMatchingName = cityRepository.findByNameIgnoreCase(name);
        final Optional<City> optionalCity = citiesMatchingName.stream().filter(city -> city.getCountry().getId().equals(countryId)).findAny();
        optionalCity.ifPresent(city ->
                CityNameUniquenessValidator
                        .super
                        .validate(id, city, CITY_NAME_ALREADY_EXISTS, new Object[]{name, countryId})
        );
    }

    /**
     * Gets exception util.
     *
     * @return Exception util.
     * @see CityNameUniquenessValidator#getExceptionUtil()
     */
    @Override
    public ExceptionUtil getExceptionUtil() {
        return exceptionUtil;
    }

}

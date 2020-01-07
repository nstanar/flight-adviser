package com.htec.flight_management.service.dto.converter;

import com.htec.domain_starter.service.dto.converter.DtoConverter;
import com.htec.flight_management.repository.entity.Country;
import com.htec.flight_management.service.dto.CountryDto;

/**
 * @author Nikola Stanar
 * <p>
 * Dto converter for {@link CountryDto} to {@link Country} and vice-versa.
 */
public interface CountryDtoConverter extends DtoConverter<CountryDto, Country> {

}

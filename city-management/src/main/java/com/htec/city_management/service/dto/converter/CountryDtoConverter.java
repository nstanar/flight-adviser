package com.htec.city_management.service.dto.converter;

import com.htec.city_management.repository.entity.Country;
import com.htec.city_management.service.dto.CountryDto;
import com.htec.domain_starter.service.dto.converter.DtoConverter;

/**
 * @author Nikola Stanar
 * <p>
 * Dto converter for {@link CountryDto} to {@link Country} and vice-versa.
 */
public interface CountryDtoConverter extends DtoConverter<CountryDto, Country> {

}

package com.htec.flight_management.service.dto.converter;

import com.htec.domain_starter.service.dto.converter.DtoConverter;
import com.htec.flight_management.repository.entity.City;
import com.htec.flight_management.service.dto.CityDto;

/**
 * @author Nikola Stanar
 * <p>
 * Dto converter for {@link CityDto} to {@link City} and vice-versa.
 */
public interface CityDtoConverter extends DtoConverter<CityDto, City, Long> {

}

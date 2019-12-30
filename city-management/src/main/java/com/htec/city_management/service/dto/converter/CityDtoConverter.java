package com.htec.city_management.service.dto.converter;

import com.htec.city_management.repository.entity.City;
import com.htec.city_management.service.dto.CityDto;
import com.htec.domain_starter.service.dto.converter.DtoConverter;

/**
 * @author Nikola Stanar
 * <p>
 * Dto converter for {@link CityDto} to {@link City} and vice-versa.
 */
public interface CityDtoConverter extends DtoConverter<CityDto, City> {

}

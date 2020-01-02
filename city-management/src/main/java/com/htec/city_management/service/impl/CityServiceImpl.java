package com.htec.city_management.service.impl;

import com.htec.city_management.repository.CityRepository;
import com.htec.city_management.repository.entity.City;
import com.htec.city_management.service.CityService;
import com.htec.city_management.service.dto.CityDto;
import com.htec.city_management.service.dto.converter.CityDtoConverter;
import com.htec.domain_starter.repository.SearchableRepository;
import com.htec.domain_starter.service.dto.converter.DtoConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * @author Nikola Stanar
 * <p>
 * Service exposing operations over city.
 * Implementation of {@link CityService}.
 */
@Service
@Transactional
@Validated
@Slf4j
@AllArgsConstructor
public class CityServiceImpl implements CityService {

    /**
     * Jpa repository for city.
     */
    private final CityRepository cityRepository;

    /**
     * Dto converter for city.
     */
    private final CityDtoConverter cityDtoConverter;

    /**
     * Gets dto converter.
     *
     * @return Check {@link DtoConverter}.
     * @see CityService#getDtoConverter()
     */
    @Override
    public DtoConverter<CityDto, City> getDtoConverter() {
        return cityDtoConverter;
    }

    /**
     * Gets searchable repository.
     *
     * @return Check {@link SearchableRepository}.
     * @see CityService#getRepository()
     */
    @Override
    public SearchableRepository<City> getRepository() {
        return cityRepository;
    }
}

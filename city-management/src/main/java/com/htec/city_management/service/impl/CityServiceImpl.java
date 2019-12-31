package com.htec.city_management.service.impl;

import com.htec.city_management.repository.CityRepository;
import com.htec.city_management.service.CityService;
import com.htec.city_management.service.dto.CityDto;
import com.htec.city_management.service.dto.converter.CityDtoConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

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
     * Finds optional city by id.
     *
     * @param id Id of the city.
     * @return Optional city.
     * @see CityService#findBy(Long)
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CityDto> findBy(final Long id) {
        return cityRepository
                .findById(id)
                .map(cityDtoConverter::from);
    }

}

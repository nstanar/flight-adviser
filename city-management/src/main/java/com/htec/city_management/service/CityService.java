package com.htec.city_management.service;

import com.htec.city_management.service.dto.CityDto;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * @author Nikola Stanar
 * <p>
 * Service for operations over city.
 */
public interface CityService {

    /**
     * Finds optional city by id.
     *
     * @param id Id of the city.
     * @return Optional city.
     */
    Optional<CityDto> findBy(@NotNull final Long id);

}

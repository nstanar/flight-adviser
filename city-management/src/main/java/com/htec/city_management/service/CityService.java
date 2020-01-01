package com.htec.city_management.service;

import com.htec.city_management.repository.entity.City;
import com.htec.city_management.service.dto.CityDto;
import com.htec.domain_starter.service.SearchableService;

/**
 * @author Nikola Stanar
 * <p>
 * Service for operations over city.
 */
public interface CityService extends SearchableService<CityDto, City> {

}

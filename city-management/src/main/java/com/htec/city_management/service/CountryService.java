package com.htec.city_management.service;

import com.htec.city_management.repository.entity.Country;
import com.htec.city_management.service.dto.CountryDto;
import com.htec.domain_starter.service.SearchableService;

/**
 * @author Nikola Stanar
 * <p>
 * Service for operations over country.
 */
public interface CountryService extends SearchableService<CountryDto, Country> {

}

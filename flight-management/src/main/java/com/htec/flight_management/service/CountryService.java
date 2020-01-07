package com.htec.flight_management.service;

import com.htec.domain_starter.service.SearchableService;
import com.htec.flight_management.repository.entity.Country;
import com.htec.flight_management.service.dto.CountryDto;

/**
 * @author Nikola Stanar
 * <p>
 * Service for operations over country.
 */
public interface CountryService extends SearchableService<CountryDto, Country> {

}

package com.htec.city_management.controller.impl;

import com.htec.city_management.controller.model.CityModel;
import com.htec.city_management.controller.model.CountryModel;
import com.htec.city_management.controller.model.assembler.impl.CityModelAssembler;
import com.htec.city_management.controller.model.assembler.impl.CountryModelAssembler;
import com.htec.city_management.repository.entity.Country;
import com.htec.city_management.service.CityService;
import com.htec.city_management.service.CountryService;
import com.htec.city_management.service.dto.CityDto;
import com.htec.city_management.service.dto.CountryDto;
import com.htec.domain_starter.controller.SearchableController;
import com.htec.domain_starter.controller.validation.exception.handler.ControllerAdvice;
import com.htec.domain_starter.service.SearchableService;
import com.htec.domain_starter.service.validation.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Nikola Stanar
 * <p>
 * Rest controller exposing API operations over country.
 * <p>
 */
@RestController
@RequestMapping("/countries")
@AllArgsConstructor
public class CountryController implements SearchableController<CountryModel, CountryDto, Country> {

    /**
     * Country service.
     */
    private final CountryService countryService;

    /**
     * Assembler for country model.
     */
    private final CountryModelAssembler countryModelAssembler;

    /**
     * City service.
     */
    private final CityService cityService;

    /**
     * Assembler for city model.
     */
    private final CityModelAssembler cityModelAssembler;

    /**
     * Finds page of cities belonging to country.
     *
     * @param countryId               Country id.
     * @param pageable                Check {@link Pageable}.
     * @param pagedResourcesAssembler Check {@link PagedResourcesAssembler}.
     * @return Page of cities belonging to country.
     */
    @GetMapping("/{countryId}/cities")
    public ResponseEntity<PagedModel<EntityModel<CityModel>>> findBy(@PathVariable final Long countryId, final Pageable pageable, final PagedResourcesAssembler<CityModel> pagedResourcesAssembler) {
        final Page<CityModel> cities = cityService
                .findAllByCountryId(countryId, pageable)
                .map(cityModelAssembler::toModel);

        return ResponseEntity.ok(pagedResourcesAssembler.toModel(cities));
    }

    /**
     * Adds city to existing country.
     *
     * @param countryId Country id.
     * @param city      City to be created and added to country.
     * @return 200 with city in response body or 404 with exception message.
     * @see ControllerAdvice#handle(NotFoundException)
     */
    @PostMapping("/{countryId}/cities")
    public ResponseEntity<CityModel> createAndAssignTo(@PathVariable final Long countryId, @RequestBody final CityDto city) {
        city.setCountryId(countryId);

        final CityDto createdCity = cityService.createFrom(city);
        final CityModel model = cityModelAssembler.toModel(createdCity);

        return ResponseEntity.ok(model);
    }

    /**
     * Gets searchable service.
     *
     * @return Searchable service.
     * @see SearchableController#getService()
     */
    @Override
    public SearchableService<CountryDto, Country> getService() {
        return countryService;
    }

    /**
     * Gets model assembler.
     *
     * @return Model assembler.
     * @see SearchableController#getModelAssembler()
     */
    @Override
    public RepresentationModelAssembler<CountryDto, CountryModel> getModelAssembler() {
        return countryModelAssembler;
    }

}
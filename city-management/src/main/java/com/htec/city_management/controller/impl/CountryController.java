package com.htec.city_management.controller.impl;

import com.htec.city_management.controller.model.CityModel;
import com.htec.city_management.controller.model.CountryModel;
import com.htec.city_management.controller.model.assembler.impl.CityModelAssembler;
import com.htec.city_management.controller.model.assembler.impl.CountryModelAssembler;
import com.htec.city_management.service.CountryService;
import com.htec.city_management.service.dto.CityDto;
import com.htec.city_management.service.dto.CountryDto;
import com.htec.domain_starter.controller.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

import static com.htec.city_management.common.constants.MessageSourceKeys.COUNTRY_DOES_NOT_EXIST;
import static org.springframework.context.i18n.LocaleContextHolder.getLocale;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Nikola Stanar
 * <p>
 * Rest controller exposing API operations over country.
 * <p>
 * //TODO: add validation messages, check uniquenes of city and country, revise indexes
 */
@RestController
@RequestMapping("/countries")
@AllArgsConstructor
public class CountryController {

    /**
     * Country service.
     */
    private final CountryService countryService;

    /**
     * Assembler for country model.
     */
    private final CountryModelAssembler countryModelAssembler;

    /**
     * Assembler for city model.
     */
    private final CityModelAssembler cityModelAssembler;

    /**
     * Message source.
     */
    private final MessageSource messageSource;

    /**
     * Finds by of countries matching name prefix (if present).
     *
     * @param namePrefix              Name prefix of the country.
     * @param pageable                Check {@link Pageable}.
     * @param pagedResourcesAssembler Check {@link PagedResourcesAssembler}.
     * @return Page of countries.
     */
    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<CountryModel>>> findAllBy(@RequestParam(required = false) final String namePrefix, final Pageable pageable, final PagedResourcesAssembler<CountryModel> pagedResourcesAssembler) {
        final Page<CountryModel> countries = countryService
                .findBy(namePrefix, pageable)
                .map(countryModelAssembler::toModel);

        return ResponseEntity.ok(pagedResourcesAssembler.toModel(countries));
    }

    /**
     * Finds country by id.
     *
     * @param id Id of the country,
     * @return Country.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CountryModel> findBy(@PathVariable final Long id) {
        return countryService
                .findBy(id)
                .map(countryModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> {
                    final String message = messageSource.getMessage(COUNTRY_DOES_NOT_EXIST, new Object[]{id}, getLocale());
                    return new NotFoundException(message);
                });
    }

    /**
     * Creates country from request body.
     *
     * @param country Request body.
     * @return 201 with location header.
     */
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody final CountryDto country) {
        final Long id = countryService.create(country);
        final URI location = linkTo(methodOn(getClass()).findBy(id)).toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Finds page of cities belonging to country.
     *
     * @param countryId               Country id.
     * @param pageable                Check {@link Pageable}.
     * @param pagedResourcesAssembler Check {@link PagedResourcesAssembler}.
     * @return Page of cities belonging to country.
     */
    @GetMapping("/{countryId}/cities")
    public ResponseEntity<PagedModel<EntityModel<CityModel>>> findAllBy(@PathVariable final Long countryId, final Pageable pageable, final PagedResourcesAssembler<CityModel> pagedResourcesAssembler) {
        final Page<CityModel> cities = countryService
                .findAllBy(countryId, pageable)
                .map(cityModelAssembler::toModel);

        return ResponseEntity.ok(pagedResourcesAssembler.toModel(cities));
    }

    /**
     * Adds city to existing country.
     *
     * @param countryId Country id.
     * @param city      City to be created and added to country.
     * @return 201 with locations header.
     */
    @PostMapping("/{countryId}/cities")
    public ResponseEntity<Void> addOwningAssociationBetween(@PathVariable final Long countryId, @RequestBody final CityDto city) {
        final Optional<CityDto> optionalCity = countryService.addOwningAssociationBetween(countryId, city);
        if (optionalCity.isEmpty()) {
            final String message = messageSource.getMessage(COUNTRY_DOES_NOT_EXIST, new Object[]{countryId}, getLocale());
            throw new NotFoundException(message);
        }

        final URI location = linkTo(methodOn
                (CityController.class).findBy(optionalCity.get().getId())
        ).toUri();

        return ResponseEntity.created(location).build();
    }

}
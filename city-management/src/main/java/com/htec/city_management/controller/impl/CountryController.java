package com.htec.city_management.controller.impl;

import com.htec.city_management.controller.model.CityModel;
import com.htec.city_management.controller.model.CountryModel;
import com.htec.city_management.controller.model.assembler.impl.CityModelAssembler;
import com.htec.city_management.controller.model.assembler.impl.CountryModelAssembler;
import com.htec.city_management.repository.entity.Country;
import com.htec.city_management.service.CountryService;
import com.htec.city_management.service.dto.CityDto;
import com.htec.city_management.service.dto.CountryDto;
import com.htec.domain_starter.controller.SearchableController;
import com.htec.domain_starter.controller.exception.NotFoundException;
import com.htec.domain_starter.controller.validation.exception.handler.ControllerAdvice;
import com.htec.domain_starter.service.SearchableService;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static com.htec.domain_starter.common.constants.MessageSourceKeys.RESOURCE_DOES_NOT_EXIST;
import static org.springframework.context.i18n.LocaleContextHolder.getLocale;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
     * Assembler for city model.
     */
    private final CityModelAssembler cityModelAssembler;

    /**
     * Message source.
     */
    private final MessageSource messageSource;

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
        final Page<CityModel> cities = countryService
                .findBy(countryId, pageable)
                .map(cityModelAssembler::toModel);

        return ResponseEntity.ok(pagedResourcesAssembler.toModel(cities));
    }

    /**
     * Adds city to existing country.
     *
     * @param countryId Country id.
     * @param city      City to be created and added to country.
     * @return 201 with locations header or 404 with exception message.
     * @see ControllerAdvice#handle(NotFoundException)
     */
    @PostMapping("/{countryId}/cities")
    public ResponseEntity<?> createAndAssignFrom(@PathVariable final Long countryId, @RequestBody final CityDto city) {
        return countryService.createAndAssignFrom(countryId, city)
                .map(cityDto -> {
                    final URI location = linkTo(methodOn
                            (CityController.class).findBy(cityDto.getId())
                    ).toUri();
                    return ResponseEntity.created(location).build();
                })
                .orElseThrow(() -> {
                    final String message = messageSource.getMessage(RESOURCE_DOES_NOT_EXIST, new Object[]{countryId}, getLocale());
                    return new NotFoundException(message);
                });
    }

    /**
     * Gets searchable service.
     *
     * @return Searchable service.
     * @see SearchableController#getUserService()
     */
    @Override
    public SearchableService<CountryDto, Country> getUserService() {
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

    /**
     * Gets message source.
     *
     * @return Message source.
     * @see SearchableController#getMessageSource()
     */
    @Override
    public MessageSource getMessageSource() {
        return messageSource;
    }

}
package com.htec.flight_management.controller.impl;

import com.htec.domain_starter.controller.SearchableController;
import com.htec.domain_starter.controller.validation.exception.handler.ControllerAdvice;
import com.htec.domain_starter.service.SearchableService;
import com.htec.domain_starter.service.validation.exception.BusinessValidationException;
import com.htec.domain_starter.service.validation.exception.NotFoundException;
import com.htec.flight_management.controller.model.AirportModel;
import com.htec.flight_management.controller.model.CityModel;
import com.htec.flight_management.controller.model.CommentModel;
import com.htec.flight_management.controller.model.assembler.impl.AirportModelAssembler;
import com.htec.flight_management.controller.model.assembler.impl.CityModelAssembler;
import com.htec.flight_management.controller.model.assembler.impl.CommentModelAssembler;
import com.htec.flight_management.repository.entity.City;
import com.htec.flight_management.service.AirportService;
import com.htec.flight_management.service.CityService;
import com.htec.flight_management.service.CommentService;
import com.htec.flight_management.service.dto.AirportDto;
import com.htec.flight_management.service.dto.CityDto;
import com.htec.flight_management.service.dto.CommentDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;

/**
 * @author Nikola Stanar
 * <p>
 * Controller exposing API operations over city.
 */
@RestController
@RequestMapping("/cities")
@AllArgsConstructor
public class CityController implements SearchableController<CityModel, CityDto, City> {

    /**
     * City service.
     */
    private final CityService cityService;

    /**
     * Model assembler for city.
     */
    private final CityModelAssembler cityModelAssembler;

    /**
     * Comment service.
     */
    private final CommentService commentService;

    /**
     * Model assembler for comment.
     */
    private final CommentModelAssembler commentModelAssembler;

    /**
     * Airports service.
     */
    private final AirportService airportService;

    /**
     * Model assembler for airport.
     */
    private final AirportModelAssembler airportModelAssembler;

    /**
     * Finds page of comments belonging to city.
     *
     * @param cityId                  City id.
     * @param pageable                Check {@link Pageable}.
     * @param pagedResourcesAssembler Check {@link PagedResourcesAssembler}.
     * @return Page of comments belonging to city.
     */
    @GetMapping("/{cityId}/comments")
    public ResponseEntity<PagedModel<EntityModel<CommentModel>>> findCommentsBy(@PathVariable final Long cityId, final Pageable pageable, final PagedResourcesAssembler<CommentModel> pagedResourcesAssembler) {
        SearchableController.super.validateExistence(cityId);

        final Page<CommentModel> comments = commentService
                .findByCityId(cityId, pageable)
                .map(commentModelAssembler::toModel);

        return ResponseEntity.ok(pagedResourcesAssembler.toModel(comments));
    }

    /**
     * Adds comment to existing city.
     *
     * @param cityId  City id.
     * @param comment Comment to be created and added to city.
     * @return 200 with model in request body; otherwise one of (404, 400) with exception message.
     * @see ControllerAdvice#handle(BusinessValidationException)
     * @see ControllerAdvice#handle(ConstraintViolationException)
     * @see ControllerAdvice#handle(NotFoundException)
     */
    @PostMapping("/{cityId}/comments")
    public ResponseEntity<CommentModel> createAndAssignTo(@PathVariable final Long cityId, @RequestBody final CommentDto comment) {
        comment.setCityId(cityId);

        final CommentDto createdComment = commentService.createFrom(comment);
        final CommentModel model = commentModelAssembler.toModel(createdComment);

        return ResponseEntity.ok(model);
    }

    /**
     * Finds page of airports belonging to city.
     *
     * @param cityId                  City id.
     * @param pageable                Check {@link Pageable}.
     * @param pagedResourcesAssembler Check {@link PagedResourcesAssembler}.
     * @return Page of airports belonging to city.
     */
    @GetMapping("/{cityId}/airports")
    public ResponseEntity<PagedModel<EntityModel<AirportModel>>> findAirportsBy(@PathVariable final Long cityId, final Pageable pageable, final PagedResourcesAssembler<AirportModel> pagedResourcesAssembler) {
        SearchableController.super.validateExistence(cityId);

        final Page<AirportModel> airports = airportService
                .findByCityId(cityId, pageable)
                .map(airportModelAssembler::toModel);

        return ResponseEntity.ok(pagedResourcesAssembler.toModel(airports));
    }

    /**
     * Adds airport to existing city.
     *
     * @param cityId  City id.
     * @param airport Airport to be created and added to city.
     * @return 200 with model in request body; otherwise one of (404, 400) with exception message.
     * @see ControllerAdvice#handle(BusinessValidationException)
     * @see ControllerAdvice#handle(ConstraintViolationException)
     * @see ControllerAdvice#handle(NotFoundException)
     */
    @PostMapping("/{cityId}/airports")
    public ResponseEntity<AirportModel> createAndAssignTo(@PathVariable final Long cityId, @RequestBody final AirportDto airport) {
        airport.setCityId(cityId);

        final AirportDto createdAirport = airportService.createFrom(airport);
        final AirportModel model = airportModelAssembler.toModel(createdAirport);

        return ResponseEntity.ok(model);
    }

    /**
     * Gets searchable service.
     *
     * @return Searchable service.
     * @see SearchableController#getService()
     */
    @Override
    public SearchableService<CityDto, City> getService() {
        return cityService;
    }

    /**
     * Gets model assembler.
     *
     * @return Model assembler.
     * @see SearchableController#getModelAssembler()
     */
    @Override
    public RepresentationModelAssembler<CityDto, CityModel> getModelAssembler() {
        return cityModelAssembler;
    }

}

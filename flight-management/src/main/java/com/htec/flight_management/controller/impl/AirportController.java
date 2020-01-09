package com.htec.flight_management.controller.impl;

import com.htec.domain_starter.controller.PagingAndSortingController;
import com.htec.domain_starter.controller.SearchableController;
import com.htec.domain_starter.controller.validation.exception.handler.ControllerAdvice;
import com.htec.domain_starter.service.SearchableService;
import com.htec.domain_starter.service.validation.exception.BusinessValidationException;
import com.htec.domain_starter.service.validation.exception.NotFoundException;
import com.htec.flight_management.controller.model.AirportModel;
import com.htec.flight_management.controller.model.FlightModel;
import com.htec.flight_management.controller.model.assembler.impl.AirportModelAssembler;
import com.htec.flight_management.controller.model.assembler.impl.FlightModelAssembler;
import com.htec.flight_management.repository.entity.Airport;
import com.htec.flight_management.service.AirportService;
import com.htec.flight_management.service.FlightService;
import com.htec.flight_management.service.dto.AirportDto;
import com.htec.flight_management.service.dto.FlightDto;
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
 * Rest controller exposing API operations over airport.
 * @see PagingAndSortingController
 */
@RestController
@RequestMapping("/airports")
@AllArgsConstructor
public class AirportController implements SearchableController<AirportModel, AirportDto, Airport> {

    /**
     * Airport service.
     */
    private final AirportService airportService;

    /**
     * Airport model assembler.
     */
    private final AirportModelAssembler airportModelAssembler;

    /**
     * Flight service.
     */
    private final FlightService flightService;

    /**
     * Flight model assembler.
     */
    private final FlightModelAssembler flightModelAssembler;

    /**
     * Finds page of flights belonging to source airport.
     *
     * @param sourceAirportId         Source airport id.
     * @param pageable                Check {@link Pageable}.
     * @param pagedResourcesAssembler Check {@link PagedResourcesAssembler}.
     * @return Page of flights belonging to source airport.
     */
    @GetMapping("/{sourceAirportId}/flights")
    public ResponseEntity<PagedModel<EntityModel<FlightModel>>> findCommentsBy(@PathVariable final Long sourceAirportId, final Pageable pageable, final PagedResourcesAssembler<FlightModel> pagedResourcesAssembler) {
        SearchableController.super.validateExistence(sourceAirportId);

        final Page<FlightModel> flights = flightService
                .findBySourceId(sourceAirportId, pageable)
                .map(flightModelAssembler::toModel);

        return ResponseEntity.ok(pagedResourcesAssembler.toModel(flights));
    }

    /**
     * Adds flight to existing source airport.
     *
     * @param sourceAirportId Source airport id.
     * @param flight          Flight to be created and added to airport.
     * @return 200 with model in request body; otherwise one of (404, 400) with exception message.
     * @see ControllerAdvice#handle(BusinessValidationException)
     * @see ControllerAdvice#handle(ConstraintViolationException)
     * @see ControllerAdvice#handle(NotFoundException)
     */
    @PostMapping("/{sourceAirportId}/flights")
    public ResponseEntity<FlightModel> createAndAssignTo(@PathVariable final Long sourceAirportId, @RequestBody final FlightDto flight) {
        flight.setSourceAirportId(sourceAirportId);

        final FlightDto createdFlight = flightService.createFrom(flight);
        final FlightModel model = flightModelAssembler.toModel(createdFlight);

        return ResponseEntity.ok(model);
    }

    /**
     * Gets service.
     *
     * @return Service.
     * @see PagingAndSortingController#getService()
     */
    @Override
    public SearchableService<AirportDto, Airport> getService() {
        return airportService;
    }

    /**
     * Gets model assembler.
     *
     * @return Model assembler.
     * @see PagingAndSortingController#getModelAssembler()
     */
    @Override
    public RepresentationModelAssembler<AirportDto, AirportModel> getModelAssembler() {
        return airportModelAssembler;
    }

}

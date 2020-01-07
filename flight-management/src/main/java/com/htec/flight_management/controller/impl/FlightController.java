package com.htec.flight_management.controller.impl;

import com.htec.domain_starter.controller.PagingAndSortingController;
import com.htec.domain_starter.service.PagingAndSortingService;
import com.htec.flight_management.controller.model.FlightModel;
import com.htec.flight_management.controller.model.assembler.impl.FlightModelAssembler;
import com.htec.flight_management.repository.entity.Flight;
import com.htec.flight_management.service.FlightService;
import com.htec.flight_management.service.dto.FlightDto;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nikola Stanar
 * <p>
 * Rest controller exposing API operations over flight.
 * @see PagingAndSortingController
 */
@RestController
@RequestMapping("/flights")
@AllArgsConstructor
public class FlightController implements PagingAndSortingController<FlightModel, FlightDto, Flight, Long> {

    /**
     * Flight service.
     */
    private final FlightService service;

    /**
     * Model assembler for flight.
     */
    private final FlightModelAssembler modelAssembler;

    /**
     * Gets service.
     *
     * @return Service.
     * @see PagingAndSortingController#getService()
     */
    @Override
    public PagingAndSortingService<FlightDto, Flight, Long> getService() {
        return service;
    }

    /**
     * Gets model assembler.
     *
     * @return Model assembler.
     * @see PagingAndSortingController#getModelAssembler()
     */
    @Override
    public RepresentationModelAssembler<FlightDto, FlightModel> getModelAssembler() {
        return modelAssembler;
    }

}

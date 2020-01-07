package com.htec.flight_management.controller.impl;

import com.htec.domain_starter.controller.PagingAndSortingController;
import com.htec.domain_starter.service.PagingAndSortingService;
import com.htec.flight_management.controller.model.AirlineModel;
import com.htec.flight_management.controller.model.assembler.impl.AirlineModelAssembler;
import com.htec.flight_management.repository.entity.Airline;
import com.htec.flight_management.service.AirlineService;
import com.htec.flight_management.service.dto.AirlineDto;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nikola Stanar
 * <p>
 * Rest controller exposing API operations over airline.
 */
@RestController
@RequestMapping("/airlines")
@AllArgsConstructor
public class AirlineController implements PagingAndSortingController<AirlineModel, AirlineDto, Airline> {

    /**
     * Airline service.
     */
    private final AirlineService airlineService;

    /**
     * Airline model assembler.
     */
    private final AirlineModelAssembler airlineModelAssembler;

    /**
     * Gets service.
     *
     * @return Service.
     * @see PagingAndSortingController#getService()
     */
    @Override
    public PagingAndSortingService<AirlineDto, Airline> getService() {
        return airlineService;
    }

    /**
     * Gets model assembler.
     *
     * @return Model assembler.
     * @see PagingAndSortingController#getModelAssembler()
     */
    @Override
    public RepresentationModelAssembler<AirlineDto, AirlineModel> getModelAssembler() {
        return airlineModelAssembler;
    }

}

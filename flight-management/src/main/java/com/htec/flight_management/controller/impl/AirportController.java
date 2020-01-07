package com.htec.flight_management.controller.impl;

import com.htec.domain_starter.controller.PagingAndSortingController;
import com.htec.domain_starter.service.PagingAndSortingService;
import com.htec.flight_management.controller.model.AirportModel;
import com.htec.flight_management.controller.model.assembler.impl.AirportModelAssembler;
import com.htec.flight_management.repository.entity.Airport;
import com.htec.flight_management.service.AirportService;
import com.htec.flight_management.service.dto.AirportDto;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nikola Stanar
 * <p>
 * Rest controller exposing API operations over airport.
 * @see PagingAndSortingController
 */
@RestController
@RequestMapping("/airports")
@AllArgsConstructor
public class AirportController implements PagingAndSortingController<AirportModel, AirportDto, Airport, Long> {

    /**
     * Airport service.
     */
    private final AirportService service;

    /**
     * Airport model assembler.
     */
    private final AirportModelAssembler modelAssembler;

    /**
     * Gets service.
     *
     * @return Service.
     * @see PagingAndSortingController#getService()
     */
    @Override
    public PagingAndSortingService<AirportDto, Airport, Long> getService() {
        return service;
    }

    /**
     * Gets model assembler.
     *
     * @return Model assembler.
     * @see PagingAndSortingController#getModelAssembler()
     */
    @Override
    public RepresentationModelAssembler<AirportDto, AirportModel> getModelAssembler() {
        return modelAssembler;
    }

}

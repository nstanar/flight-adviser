package com.htec.flight_management.controller.model.assembler.impl;

import com.htec.flight_management.controller.impl.FlightController;
import com.htec.flight_management.controller.model.FlightModel;
import com.htec.flight_management.service.dto.FlightDto;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Nikola Stanar
 * <p>
 * Model assembler for {@link FlightDto} to {@link FlightModel}.
 */
@Component
@AllArgsConstructor
public class FlightModelAssembler implements RepresentationModelAssembler<FlightDto, FlightModel> {

    /**
     * Assembles flight model from flight dto.
     *
     * @param dto Dto.
     * @return Assembled flight model.
     * @see RepresentationModelAssembler#toModel(Object)
     */
    @Override
    public FlightModel toModel(final FlightDto dto) {
        final FlightModel model = FlightModel.builder()
                .destinationAirportId(dto.getDestinationAirportId())
                .airlineCode(dto.getAirlineCode())
                .stops(dto.getStops())
                .price(dto.getPrice())
                .distanceInKm(dto.getDistanceInKm())
                .build();

        // Add self link.
        final Link selfLink = linkTo(methodOn
                (FlightController.class).findBy(dto.getId())
        ).withSelfRel();

        model.add(selfLink);

        return model;
    }

}

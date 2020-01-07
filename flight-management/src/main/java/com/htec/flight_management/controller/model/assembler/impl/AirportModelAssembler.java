package com.htec.flight_management.controller.model.assembler.impl;

import com.htec.flight_management.controller.impl.AirportController;
import com.htec.flight_management.controller.model.AirportModel;
import com.htec.flight_management.service.dto.AirportDto;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Nikola Stanar
 * <p>
 * Model assembler for {@link AirportDto} to {@link AirportModel}.
 */
@Component
@NoArgsConstructor
public class AirportModelAssembler implements RepresentationModelAssembler<AirportDto, AirportModel> {

    /**
     * Assembles airport model from dto.
     *
     * @param dto Dto.
     * @return Assembled model.
     * @see RepresentationModelAssembler#toModel(Object)
     */
    @Override
    public AirportModel toModel(final AirportDto dto) {
        final AirportModel model = AirportModel.builder()
                .name(dto.getName())
                .code(dto.getCode())
                .cityName(dto.getCityName())
                .countryName(dto.getCountryName())
                .build();

        // Add self link.
        final Link selfLink = WebMvcLinkBuilder.linkTo(methodOn
                (AirportController.class).findBy(dto.getId())
        ).withSelfRel();

        model.add(selfLink);

        return model;
    }
}

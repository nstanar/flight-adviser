package com.htec.flight_management.controller.model.assembler.impl;

import com.htec.flight_management.controller.impl.AirportController;
import com.htec.flight_management.controller.impl.CityController;
import com.htec.flight_management.controller.model.AirportModel;
import com.htec.flight_management.service.dto.AirportDto;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static com.htec.flight_management.common.constants.HypermediaRelNames.IN_CITY;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
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
                .iataCode(dto.getIataCode())
                .icaoCode(dto.getIcaoCode())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .build();

        // Add self link.
        final Link selfLink = linkTo(methodOn
                (AirportController.class).findBy(dto.getId())
        ).withSelfRel();

        model.add(selfLink);

        // Add city link.
        final Link cityLink = linkTo(methodOn
                (CityController.class).findBy(dto.getCityId())
        ).withRel(IN_CITY);

        model.add(cityLink);

        return model;
    }
}

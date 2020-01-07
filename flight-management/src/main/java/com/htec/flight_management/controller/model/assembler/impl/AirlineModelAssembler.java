package com.htec.flight_management.controller.model.assembler.impl;

import com.htec.flight_management.controller.impl.AirlineController;
import com.htec.flight_management.controller.model.AirlineModel;
import com.htec.flight_management.service.dto.AirlineDto;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Nikola Stanar
 * <p>
 * Model assemlber for {@link AirlineDto} to {@link AirlineModel}.
 */
@Component
@NoArgsConstructor
public class AirlineModelAssembler implements RepresentationModelAssembler<AirlineDto, AirlineModel> {

    /**
     * Assembles airline model from dto.
     *
     * @param dto Dto.
     * @return Assembled model.
     */
    @Override
    public AirlineModel toModel(final AirlineDto dto) {
        final AirlineModel model = AirlineModel.builder()
                .code(dto.getCode())
                .build();

        final Link selfLink = linkTo(methodOn
                (AirlineController.class).findBy(dto.getId())
        ).withSelfRel();

        model.add(selfLink);

        return model;
    }

}

package com.htec.flight_management.controller.model.assembler.impl;

import com.htec.flight_management.controller.impl.CityController;
import com.htec.flight_management.controller.impl.CommentController;
import com.htec.flight_management.controller.model.CommentModel;
import com.htec.flight_management.service.dto.CommentDto;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static com.htec.flight_management.common.constants.HypermediaRelNames.FOR_CITY;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Nikola Stanar
 * <p>
 * Model assembler for {@link CommentDto} to {@link CommentModel}.
 */
@Component
@NoArgsConstructor
public class CommentModelAssembler implements RepresentationModelAssembler<CommentDto, CommentModel> {

    /**
     * Assembles comment model from dto.
     *
     * @param dto Comment dto to be assembled from.
     * @return Assembled comment model.
     */
    @Override
    public CommentModel toModel(final CommentDto dto) {
        final CommentModel commentModel = CommentModel.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .createdBy(dto.getCreatedBy())
                .createdDate(dto.getCreatedDate())
                .modifiedBy(dto.getModifiedBy())
                .modifiedDate(dto.getModifiedDate())
                .build();

        // Add self link.
        final Link selfLink = linkTo(methodOn
                (CommentController.class).findBy(dto.getId())
        ).withSelfRel();

        commentModel.add(selfLink);

        // Add city link.
        final Link cityLink = linkTo(methodOn
                (CityController.class).findBy(dto.getCityId())
        ).withRel(FOR_CITY);

        commentModel.add(cityLink);

        return commentModel;
    }

}

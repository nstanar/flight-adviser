package com.htec.user_management.user.controller.representation_model.assembler.impl;

import com.htec.user_management.user.controller.UserController;
import com.htec.user_management.user.controller.representation_model.UserRepresentationModel;
import com.htec.user_management.user.service.dto.UserDto;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Nikola Stanar
 * <p>
 * Assembler for {@link UserDto} --> {@link UserRepresentationModel}.
 */
@Component
@NoArgsConstructor
public class UserRepresentationModelAssembler implements RepresentationModelAssembler<UserDto, UserRepresentationModel> {

    /**
     * Assembles user representation model from user dto.
     *
     * @param dto User dto to be used in assembly process.
     * @return User representation model.
     */
    @Override
    public UserRepresentationModel toModel(final UserDto dto) {
        final UserRepresentationModel userRepresentationModel = UserRepresentationModel.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .username(dto.getUsername())
                .build();

        /* Add self link. */
        final Link selfLink = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(UserController.class).findBy(dto.getId())
        )
                .withSelfRel();
        userRepresentationModel.add(selfLink);

        return userRepresentationModel;
    }
}

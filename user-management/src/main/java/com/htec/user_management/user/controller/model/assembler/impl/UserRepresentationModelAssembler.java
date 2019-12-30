package com.htec.user_management.user.controller.model.assembler.impl;

import com.htec.user_management.user.controller.UserController;
import com.htec.user_management.user.controller.model.UserModel;
import com.htec.user_management.user.service.dto.UserDto;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Nikola Stanar
 * <p>
 * Assembler for {@link UserDto} to {@link UserModel}.
 */
@Component
@NoArgsConstructor
public class UserRepresentationModelAssembler implements RepresentationModelAssembler<UserDto, UserModel> {

    /**
     * Assembles user representation model from user dto.
     *
     * @param dto User dto to be used in assembly process.
     * @return User representation model.
     * @see RepresentationModelAssembler#toModel(Object)
     */
    @Override
    public UserModel toModel(final UserDto dto) {
        final UserModel userModel = UserModel.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .username(dto.getUsername())
                .build();

        /* Add self link. */
        final Link selfLink = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(UserController.class).findBy(dto.getId())
        )
                .withSelfRel();
        userModel.add(selfLink);

        return userModel;
    }
}

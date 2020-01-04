package com.htec.user_management.user.controller.model.assembler.impl;

import com.htec.user_management.user.controller.impl.UserController;
import com.htec.user_management.user.controller.model.UserModel;
import com.htec.user_management.user.service.dto.UserDto;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static com.htec.user_management.common.constants.HypermediaRelNames.HAVING_ROLES;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Nikola Stanar
 * <p>
 * Assembler for {@link UserDto} to {@link UserModel}.
 */
@Component
@NoArgsConstructor
public class UserModelAssembler implements RepresentationModelAssembler<UserDto, UserModel> {

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
                .createdBy(dto.getCreatedBy())
                .createdDate(dto.getCreatedDate())
                .modifiedBy(dto.getModifiedBy())
                .modifiedDate(dto.getModifiedDate())
                .build();

        // Add self link.
        final Link selfLink = linkTo(
                methodOn(UserController.class).findBy(dto.getId())
        ).withSelfRel();

        userModel.add(selfLink);

        // Add roles link.
        final Link rolesLink = linkTo(
                methodOn(UserController.class).findRolesBy(dto.getId())
        ).withRel(HAVING_ROLES);

        userModel.add(rolesLink);

        return userModel;
    }
}

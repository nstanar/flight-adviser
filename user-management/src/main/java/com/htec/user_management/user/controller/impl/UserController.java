package com.htec.user_management.user.controller.impl;

import com.htec.domain_starter.controller.CrudController;
import com.htec.domain_starter.controller.SearchableController;
import com.htec.domain_starter.service.CrudService;
import com.htec.user_management.user.controller.model.RoleModel;
import com.htec.user_management.user.controller.model.UserModel;
import com.htec.user_management.user.controller.model.assembler.impl.RoleModelAssembler;
import com.htec.user_management.user.controller.model.assembler.impl.UserModelAssembler;
import com.htec.user_management.user.repository.entity.User;
import com.htec.user_management.user.service.UserService;
import com.htec.user_management.user.service.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Nikola Stanar
 * <p>
 * Rest controller exposing operations over {@link UserDto}.
 */
@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor
public class UserController implements CrudController<UserModel, UserDto, User> {

    /**
     * Service for user.
     */
    private final UserService userService;

    /**
     * Model assembler for user.
     */
    private final UserModelAssembler userModelAssembler;

    /**
     * Model assembler for role.
     */
    private final RoleModelAssembler roleModelAssembler;

    /**
     * Message source.
     */
    private final MessageSource messageSource;

    /**
     * Finds profile data of currently logged in user.
     *
     * @param principal Check {@link Principal}.
     * @return 200 with user model.
     */
    @GetMapping(value = "/me")
    public ResponseEntity<UserModel> findBy(final Principal principal) {
        return userService
                .findBy(principal.getName())
                .map(userModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .get();
    }

    /**
     * Finds user roles.
     *
     * @param userId Id of the user.
     * @return User roles.
     */
    @GetMapping("/{userId}/roles")
    public ResponseEntity<CollectionModel<EntityModel<RoleModel>>> findRolesBy(@PathVariable final Long userId) {
        final List<RoleModel> roles = userService
                .findRolesBy(userId)
                .stream()
                .map(roleModelAssembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.wrap(roles));
    }

    /**
     * Gets searchable service.
     *
     * @return Searchable service.
     * @see SearchableController#getUserService()
     */
    @Override
    public CrudService<UserDto, User> getUserService() {
        return userService;
    }

    /**
     * Gets model assembler.
     *
     * @return Model assembler.
     * @see SearchableController#getModelAssembler()
     */
    @Override
    public RepresentationModelAssembler<UserDto, UserModel> getModelAssembler() {
        return userModelAssembler;
    }

    /**
     * Gets message source.
     *
     * @return Message source.
     * @see SearchableController#getMessageSource()
     */
    @Override
    public MessageSource getMessageSource() {
        return messageSource;
    }

}

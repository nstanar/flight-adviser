package com.htec.user_management.user.controller.impl;

import com.htec.domain_starter.controller.CrudController;
import com.htec.domain_starter.controller.SearchableController;
import com.htec.domain_starter.controller.validation.exception.handler.ControllerAdvice;
import com.htec.domain_starter.service.CrudService;
import com.htec.domain_starter.service.validation.exception.BusinessValidationException;
import com.htec.domain_starter.service.validation.exception.NotFoundException;
import com.htec.user_management.user.controller.model.RoleModel;
import com.htec.user_management.user.controller.model.UserModel;
import com.htec.user_management.user.controller.model.assembler.impl.RoleModelAssembler;
import com.htec.user_management.user.controller.model.assembler.impl.UserModelAssembler;
import com.htec.user_management.user.repository.entity.User;
import com.htec.user_management.user.service.UserService;
import com.htec.user_management.user.service.dto.RoleDto;
import com.htec.user_management.user.service.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.security.Principal;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.htec.domain_starter.common.constants.MessageSourceKeys.RESOURCE_DOES_NOT_EXIST;

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
     * Finds profile data of currently logged in user.
     *
     * @param principal Check {@link Principal}.
     * @return 200 with user model.
     */
    @GetMapping(value = "/me")
    public ResponseEntity<UserModel> findBy(final Principal principal) {
        return Optional.ofNullable(principal)
                .map(p -> userService
                        .findByUsername(p.getName())
                        .map(userModelAssembler::toModel)
                        .map(ResponseEntity::ok)
                        .get()
                )
                .orElseThrow(() -> getService().getExceptionUtil().createNotFoundExceptionFrom(RESOURCE_DOES_NOT_EXIST, new Object[]{null}));
    }

    /**
     * Updates my profile.
     *
     * @param dto       Update content.
     * @param principal Check {@link Principal}.
     * @return 200 with user in response body; otherwise one of (404, 400) with exception message.
     * @see ControllerAdvice#handle(NotFoundException)
     * @see ControllerAdvice#handle(BusinessValidationException)
     * @see ControllerAdvice#handle(ConstraintViolationException)
     */
    @PutMapping(value = "/me")
    public ResponseEntity<UserModel> updateBy(@RequestBody final UserDto dto, final Principal principal) {
        return Optional.ofNullable(principal)
                .map(p -> userService.updateByUsername(p.getName(), dto))
                .map(userModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> getService().getExceptionUtil().createNotFoundExceptionFrom(RESOURCE_DOES_NOT_EXIST, new Object[]{null}));
    }

    /**
     * Deletes my profile.
     *
     * @param principal Check {@link Principal}.
     * @return 204.
     */
    @DeleteMapping(value = "/me")
    public ResponseEntity<Void> deleteBy(final Principal principal) {
        if (principal != null) {
            userService.deleteByUsername(principal.getName());
        } else {
            throw getService().getExceptionUtil().createNotFoundExceptionFrom(RESOURCE_DOES_NOT_EXIST, new Object[]{null});
        }

        return ResponseEntity.noContent().build();
    }

    /**
     * Finds user roles.
     *
     * @param userId Id of the user.
     * @return 200 with user roles in response body.
     */
    @GetMapping("/{userId}/roles")
    public ResponseEntity<CollectionModel<EntityModel<RoleModel>>> findRolesBy(@PathVariable final Long userId) {
        final Set<RoleModel> roles = userService
                .findRolesBy(userId)
                .stream()
                .map(roleModelAssembler::toModel)
                .collect(Collectors.toSet());

        return ResponseEntity.ok(CollectionModel.wrap(roles));
    }

    /**
     * Assign new role to user.
     *
     * @param userId Id of the user.
     * @param role   Role to be assigned.
     * @return 204 if successful; otherwise one of (404, 400) with exception message.
     * @see ControllerAdvice#handle(NotFoundException)
     */
    @PutMapping("/{userId}/roles")
    public ResponseEntity<Void> assignRole(@PathVariable final Long userId, @RequestBody final RoleDto role) {
        userService.assignRole(userId, role);

        return ResponseEntity.noContent().build();
    }

    /**
     * Gets searchable service.
     *
     * @return Searchable service.
     * @see SearchableController#getService()
     */
    @Override
    public CrudService<UserDto, User> getService() {
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

}

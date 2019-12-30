package com.htec.user_management.user.controller;

import com.htec.domain_starter.controller.exception.NotFoundException;
import com.htec.user_management.user.controller.model.UserModel;
import com.htec.user_management.user.controller.model.assembler.impl.UserRepresentationModelAssembler;
import com.htec.user_management.user.service.UserService;
import com.htec.user_management.user.service.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static com.htec.user_management.common.constants.MessageSourceKeys.USER_DOES_NOT_EXIST;
import static org.springframework.context.i18n.LocaleContextHolder.getLocale;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Nikola Stanar
 * <p>
 * Rest controller exposing operations over {@link UserDto}.
 */
@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor
public class UserController {

    /**
     * Service.
     */
    private final UserService service;

    /**
     * Model assembler.
     */
    private final UserRepresentationModelAssembler assembler;

    /**
     * Message source
     */
    private final MessageSource messageSource;

    /**
     * Finds page of users.
     *
     * @param pageable                Check {@link Pageable}.
     * @param pagedResourcesAssembler Check {@link PagedResourcesAssembler}.
     * @return Paged collection of users.
     */
    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<UserModel>>> findAll(final Pageable pageable, final PagedResourcesAssembler<UserModel> pagedResourcesAssembler) {
        final Page<UserModel> users = service.findAll(pageable)
                .map(assembler::toModel);

        return ResponseEntity.ok(pagedResourcesAssembler.toModel(users));
    }

    /**
     * Finds user by id.
     *
     * @param id User's id.
     * @return User representation model.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserModel> findBy(@PathVariable final Long id) {
        return service.findBy(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> {
                    final String message = messageSource.getMessage(USER_DOES_NOT_EXIST, new Object[]{id}, getLocale());
                    return new NotFoundException(message);
                });
    }

    /**
     * Creates user from a request content.
     *
     * @param user User that is about to be created.
     * @return 201 with location header.
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody final UserDto user) {
        final UserDto createdUser = service.create(user);

        final URI location = linkTo(methodOn(
                this.getClass()).findBy(createdUser.getId())
        ).toUri();


        return ResponseEntity.created(location).build();
    }

}

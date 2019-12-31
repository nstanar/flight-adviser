package com.htec.user_management.common.controller;

import com.htec.domain_starter.controller.representation_model.RootRepresentationModel;
import com.htec.user_management.user.controller.impl.UserController;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.htec.domain_starter.controller.util.ControllerLinkBuilder.buildFrom;
import static com.htec.user_management.common.constants.HypermediaRelNames.USERS_REL_NAME;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Nikola Stanar
 * <p>
 * Application rest entry-point.
 */
@RestController
@RequestMapping(value = "/")
public class RootController {

    /**
     * Api name.
     */
    private static final String API_NAME = "User management API";

    /**
     * Returns root representation model.
     *
     * @return Check {@link RootRepresentationModel}.
     */
    @GetMapping
    public ResponseEntity<RootRepresentationModel> get() {
        final RootRepresentationModel rootRepresentationModel = RootRepresentationModel.builder()
                .description(API_NAME)
                .build();

        // Add users link.
        final WebMvcLinkBuilder usersLinkBuilder = linkTo(methodOn
                (UserController.class).findAll(Pageable.unpaged(), new PagedResourcesAssembler<>(null, null))
        );
        final Link usersLink = buildFrom(USERS_REL_NAME, usersLinkBuilder, PageRequest.of(0, 20));
        rootRepresentationModel.add(usersLink);

        return ResponseEntity.ok(rootRepresentationModel);
    }

}

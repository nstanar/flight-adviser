package com.htec.user_management.common.controller;

import com.htec.domain_starter.controller.representation_model.RootRepresentationModel;
import com.htec.user_management.user.controller.UserController;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.htec.user_management.common.constants.HypermediaRelNames.USERS_REL_NAME;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

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

        final Link usersLink = linkTo(UserController.class)
                .withRel(USERS_REL_NAME);
        rootRepresentationModel.add(usersLink);

        return ResponseEntity.ok(rootRepresentationModel);
    }

}

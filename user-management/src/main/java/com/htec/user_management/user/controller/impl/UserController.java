package com.htec.user_management.user.controller.impl;

import com.htec.domain_starter.controller.CrudController;
import com.htec.domain_starter.controller.SearchableController;
import com.htec.domain_starter.service.CrudService;
import com.htec.user_management.user.controller.model.UserModel;
import com.htec.user_management.user.controller.model.assembler.impl.UserRepresentationModelAssembler;
import com.htec.user_management.user.repository.entity.User;
import com.htec.user_management.user.service.UserService;
import com.htec.user_management.user.service.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * Service.
     */
    private final UserService service;

    /**
     * Model assembler.
     */
    private final UserRepresentationModelAssembler assembler;

    /**
     * Message source.
     */
    private final MessageSource messageSource;

    /**
     * Gets searchable service.
     *
     * @return Searchable service.
     * @see SearchableController#getService()
     */
    @Override
    public CrudService<UserDto, User> getService() {
        return service;
    }

    /**
     * Gets model assembler.
     *
     * @return Model assembler.
     * @see SearchableController#getModelAssembler()
     */
    @Override
    public RepresentationModelAssembler<UserDto, UserModel> getModelAssembler() {
        return assembler;
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

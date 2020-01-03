package com.htec.city_management.controller.impl;

import com.htec.city_management.controller.model.CommentModel;
import com.htec.city_management.controller.model.assembler.impl.CommentModelAssembler;
import com.htec.city_management.repository.entity.Comment;
import com.htec.city_management.service.CommentService;
import com.htec.city_management.service.dto.CommentDto;
import com.htec.domain_starter.controller.CrudController;
import com.htec.domain_starter.controller.SearchableController;
import com.htec.domain_starter.service.CrudService;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nikola Stanar
 * <p>
 * Rest controller exposing API operations over comment.
 */
@RestController
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentController implements CrudController<CommentModel, CommentDto, Comment> {

    /**
     * Service for comment.
     */
    private final CommentService commentService;

    /**
     * Model assembler for comment.
     */
    private final CommentModelAssembler commentModelAssembler;

    /**
     * Message source.
     */
    private final MessageSource messageSource;

    /**
     * Gets CRUD service.
     *
     * @return CRUD service.
     * @see CrudController#getUserService()
     */
    @Override
    public CrudService<CommentDto, Comment> getUserService() {
        return commentService;
    }

    /**
     * Gets model assembler.
     *
     * @return Model assembler.
     * @see CrudController#getModelAssembler()
     */
    @Override
    public RepresentationModelAssembler<CommentDto, CommentModel> getModelAssembler() {
        return commentModelAssembler;
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

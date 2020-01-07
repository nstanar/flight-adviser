package com.htec.flight_management.controller.impl;

import com.htec.domain_starter.controller.PagingAndSortingController;
import com.htec.domain_starter.service.PagingAndSortingService;
import com.htec.flight_management.controller.model.CommentModel;
import com.htec.flight_management.controller.model.assembler.impl.CommentModelAssembler;
import com.htec.flight_management.repository.entity.Comment;
import com.htec.flight_management.service.CommentService;
import com.htec.flight_management.service.dto.CommentDto;
import lombok.AllArgsConstructor;
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
public class CommentController implements PagingAndSortingController<CommentModel, CommentDto, Comment, Long> {

    /**
     * Service for comment.
     */
    private final CommentService commentService;

    /**
     * Model assembler for comment.
     */
    private final CommentModelAssembler commentModelAssembler;

    /**
     * Gets CRUD service.
     *
     * @return CRUD service.
     * @see PagingAndSortingController#getService()
     */
    @Override
    public PagingAndSortingService<CommentDto, Comment, Long> getService() {
        return commentService;
    }

    /**
     * Gets model assembler.
     *
     * @return Model assembler.
     * @see PagingAndSortingController#getModelAssembler()
     */
    @Override
    public RepresentationModelAssembler<CommentDto, CommentModel> getModelAssembler() {
        return commentModelAssembler;
    }

}

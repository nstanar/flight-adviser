package com.htec.city_management.service.impl;

import com.htec.city_management.repository.CommentRepository;
import com.htec.city_management.repository.entity.Comment;
import com.htec.city_management.service.CommentService;
import com.htec.city_management.service.dto.CommentDto;
import com.htec.city_management.service.dto.converter.CommentDtoConverter;
import com.htec.domain_starter.service.dto.converter.DtoConverter;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * @author Nikola Stanar
 * <p>
 * Service exposing operations over comment.
 * @see CommentService
 */
@Service
@Transactional
@Validated
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    /**
     * Jpa repository for comment.
     */
    private final CommentRepository commentRepository;

    /**
     * Dto converter for comment.
     */
    private final CommentDtoConverter commentDtoConverter;

    /**
     * Gets jpa repository.
     *
     * @return Jpa repository.
     * @see CommentService#getRepository()
     */
    @Override
    public JpaRepository<Comment, Long> getRepository() {
        return commentRepository;
    }

    /**
     * Gets dto converter.
     *
     * @return Dto converter.
     * @see CommentService#getDtoConverter()
     */
    @Override
    public DtoConverter<CommentDto, Comment> getDtoConverter() {
        return commentDtoConverter;
    }
}

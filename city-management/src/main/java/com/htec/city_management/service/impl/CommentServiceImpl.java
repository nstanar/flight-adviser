package com.htec.city_management.service.impl;

import com.htec.city_management.repository.CommentRepository;
import com.htec.city_management.repository.entity.Comment;
import com.htec.city_management.service.CommentService;
import com.htec.city_management.service.dto.CommentDto;
import com.htec.city_management.service.dto.converter.CommentDtoConverter;
import com.htec.domain_starter.service.dto.converter.DtoConverter;
import com.htec.domain_starter.service.validation.chain.BusinessValidatorChain;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Nikola Stanar
 * <p>
 * Service exposing operations over comment.
 * @see CommentService
 */
@Service
@Slf4j
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
     * Message source.
     */
    private final MessageSource messageSource;

    /**
     * Gets business validator chain.
     *
     * @return Business validator chain.
     */
    @Override
    public Optional<BusinessValidatorChain<CommentDto>> getBusinessValidatorChain() {
        return Optional.empty();
    }

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
     * Gets message source.
     *
     * @return Message source.
     * @see CommentService#getMessageSource()
     */
    @Override
    public MessageSource getMessageSource() {
        return messageSource;
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

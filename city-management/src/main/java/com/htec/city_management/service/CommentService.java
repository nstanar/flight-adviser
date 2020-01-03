package com.htec.city_management.service;

import com.htec.city_management.repository.entity.Comment;
import com.htec.city_management.service.dto.CommentDto;
import com.htec.domain_starter.service.CrudService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * @author Nikola Stanar
 * <p>
 * Service exposing operations over comment.
 */
@Transactional
@Validated
public interface CommentService extends CrudService<CommentDto, Comment> {

}

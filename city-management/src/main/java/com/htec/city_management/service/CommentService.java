package com.htec.city_management.service;

import com.htec.city_management.repository.entity.Comment;
import com.htec.city_management.service.dto.CommentDto;
import com.htec.domain_starter.service.CrudService;

/**
 * @author Nikola Stanar
 * <p>
 * Service exposing operations over comment.
 */
public interface CommentService extends CrudService<CommentDto, Comment> {

}

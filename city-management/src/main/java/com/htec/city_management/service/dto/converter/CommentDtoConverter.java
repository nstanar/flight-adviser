package com.htec.city_management.service.dto.converter;

import com.htec.city_management.repository.entity.Comment;
import com.htec.city_management.service.dto.CommentDto;
import com.htec.domain_starter.service.dto.converter.DtoConverter;

/**
 * @author Nikola Stanar
 * <p>
 * Dto converter for {@link CommentDto} to {@link Comment} and vice-versa.
 */
public interface CommentDtoConverter extends DtoConverter<CommentDto, Comment> {

}

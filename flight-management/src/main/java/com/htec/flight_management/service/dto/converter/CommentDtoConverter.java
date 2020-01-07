package com.htec.flight_management.service.dto.converter;

import com.htec.domain_starter.service.dto.converter.DtoConverter;
import com.htec.flight_management.repository.entity.Comment;
import com.htec.flight_management.service.dto.CommentDto;

/**
 * @author Nikola Stanar
 * <p>
 * Dto converter for {@link CommentDto} to {@link Comment} and vice-versa.
 */
public interface CommentDtoConverter extends DtoConverter<CommentDto, Comment, Long> {

}

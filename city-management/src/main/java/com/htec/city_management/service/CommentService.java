package com.htec.city_management.service;

import com.htec.city_management.repository.entity.Comment;
import com.htec.city_management.service.dto.CommentDto;
import com.htec.domain_starter.service.CrudService;
import com.htec.domain_starter.service.dto.BaseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Nikola Stanar
 * <p>
 * Service exposing operations over comment.
 */
@Transactional
@Validated
public interface CommentService extends CrudService<CommentDto, Comment> {

    /**
     * Finds page of comments belonging to city.
     *
     * @param cityId   City id.
     * @param pageable Check pageable.
     * @return Page of comments belonging to city;
     */
    @PreAuthorize("isAuthenticated()")
    @Transactional(readOnly = true)
    Page<CommentDto> findAllByCityId(@NotNull final Long cityId, @NotNull final Pageable pageable);


    /**
     * Creates DTO from method argument.
     *
     * @param dto Dto holding content that is about to be created.
     * @return Id of the created dto.
     * @see CrudService#createFrom(BaseDto)
     */
    @PreAuthorize("isAuthenticated()")
    @Override
    default CommentDto createFrom(final @NotNull @Valid CommentDto dto) {
        return CrudService.super.createFrom(dto);
    }

    /**
     * Update DTO from method argument.
     *
     * @param id  Id of the dto.
     * @param dto DTO holding update content.
     * @return Optional updated DTO if id exist, else empty.
     * @see CrudService#updateFrom(Long, BaseDto)
     */
    @PostAuthorize("hasRole('ADMIN') or returnObject.createdBy==authentication.principal")
    @Override
    default CommentDto updateFrom(final @NotNull Long id, final @NotNull @Valid CommentDto dto) {
        return CrudService.super.updateFrom(id, dto);
    }

    /**
     * Deletes DTO by id.
     *
     * @param id Id of the DTO.
     * @return Optionally deleted DTO if existed.
     * @see CrudService#deleteById(Long)
     */
    @PostAuthorize("hasRole('ADMIN') or returnObject.createdBy==authentication.principal")
    @Override
    default CommentDto deleteById(final Long id) {
        return CrudService.super.deleteById(id);
    }
}

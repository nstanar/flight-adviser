package com.htec.city_management.service.dto.converter.impl;

import com.htec.city_management.repository.entity.Comment;
import com.htec.city_management.service.dto.CommentDto;
import com.htec.city_management.service.dto.converter.CommentDtoConverter;
import com.htec.domain_starter.repository.entity.BaseEntity;
import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.domain_starter.service.dto.converter.DtoConverter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author Nikola Stanar
 * <p>
 * Dto converter for {@link CommentDto} to {@link Comment} and vice-versa.
 * @see CommentDtoConverter
 */
@Component
@Validated
@NoArgsConstructor
public class CommentDtoConverterImpl implements CommentDtoConverter {

    /**
     * Converts comment entity to dto.
     *
     * @param entity Entity to be converted.
     * @return Comment DTO.
     * @see DtoConverter#from(BaseEntity)
     */
    @Override
    public CommentDto from(@NotNull final Comment entity) {
        final CommentDto dto = new CommentDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setCityId(entity.getCity().getId());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedBy(entity.getModifiedBy());
        dto.setModifiedDate(entity.getModifiedDate());
        return dto;
    }

    /**
     * Converts comment dto to entity.
     *
     * @param dto DTO to be converted.
     * @return Comment entity.
     * @see DtoConverter#from(BaseDto)
     */
    @Override
    public Comment from(@NotNull final CommentDto dto) {
        return from(dto, new Comment());
    }

    /**
     * Maps content from dto comment to existing comment entity.0
     *
     * @param dto            DTO to be converted.
     * @param existingEntity Existing ENTITY.
     * @return Existing entity with updated values.
     */
    @Override
    public Comment from(@NotNull final CommentDto dto, @NotNull final Comment existingEntity) {
        existingEntity.setTitle(dto.getTitle());
        existingEntity.setDescription(dto.getDescription());
        return existingEntity;
    }
}

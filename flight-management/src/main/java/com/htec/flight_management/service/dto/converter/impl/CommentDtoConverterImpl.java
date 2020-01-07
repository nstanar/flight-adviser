package com.htec.flight_management.service.dto.converter.impl;

import com.htec.domain_starter.repository.BaseEntity;
import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.domain_starter.service.dto.converter.DtoConverter;
import com.htec.domain_starter.service.validation.util.ExceptionUtil;
import com.htec.flight_management.repository.CityRepository;
import com.htec.flight_management.repository.entity.City;
import com.htec.flight_management.repository.entity.Comment;
import com.htec.flight_management.service.dto.CommentDto;
import com.htec.flight_management.service.dto.converter.CommentDtoConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

import static com.htec.domain_starter.common.constants.MessageSourceKeys.RESOURCE_DOES_NOT_EXIST;

/**
 * @author Nikola Stanar
 * <p>
 * Dto converter for {@link CommentDto} to {@link Comment} and vice-versa.
 * @see CommentDtoConverter
 */
@Service
@AllArgsConstructor
public class CommentDtoConverterImpl implements CommentDtoConverter {

    /**
     * Repository for city.
     */
    private final CityRepository cityRepository;

    /**
     * Exception util.
     */
    private final ExceptionUtil exceptionUtil;

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
        final Comment entity = new Comment();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        final Long cityId = dto.getCityId();
        final Optional<City> optionalCity = cityRepository.findById(cityId);
        if (optionalCity.isPresent()) {
            entity.setCity(optionalCity.get());
            return entity;
        } else {
            throw exceptionUtil.createNotFoundExceptionFrom(RESOURCE_DOES_NOT_EXIST, new Object[]{cityId});
        }
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

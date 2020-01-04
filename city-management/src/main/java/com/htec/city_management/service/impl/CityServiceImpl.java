package com.htec.city_management.service.impl;

import com.htec.city_management.repository.CityRepository;
import com.htec.city_management.repository.CommentRepository;
import com.htec.city_management.repository.entity.City;
import com.htec.city_management.repository.entity.Comment;
import com.htec.city_management.service.CityService;
import com.htec.city_management.service.dto.CityDto;
import com.htec.city_management.service.dto.CommentDto;
import com.htec.city_management.service.dto.converter.CityDtoConverter;
import com.htec.city_management.service.dto.converter.CommentDtoConverter;
import com.htec.domain_starter.repository.SearchableRepository;
import com.htec.domain_starter.service.dto.converter.DtoConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * @author Nikola Stanar
 * <p>
 * Service exposing operations over city.
 * Implementation of {@link CityService}.
 */
@Service
@Slf4j
@AllArgsConstructor
public class CityServiceImpl implements CityService {

    /**
     * Jpa repository for city.
     */
    private final CityRepository cityRepository;

    /**
     * Dto converter for city.
     */
    private final CityDtoConverter cityDtoConverter;

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
     * Finds page of comments belonging to city.
     *
     * @param cityId   City id.
     * @param pageable Check pageable.
     * @return Page of comments belonging to city;
     * @see CityService#findBy(Long, Pageable)
     */
    public Page<CommentDto> findBy(@NotNull final Long cityId, @NotNull final Pageable pageable) {
        log.info("Fetching {} of comments for city of id {}.", pageable, cityId);
        return commentRepository
                .findAllByCityId(cityId, pageable)
                .map(commentDtoConverter::from);
    }

    /**
     * Adds comment to the city of certain id.
     *
     * @param cityId  Id of the city.
     * @param comment Comment to be added.
     * @return Optional comment created if city exists.
     * @see CityService#createAndAssignFrom(Long, CommentDto)
     */
    public Optional<CommentDto> createAndAssignFrom(@NotNull final Long cityId, @NotNull @Valid final CommentDto comment) {
        log.info("Adding comment {} to city of id {}.", comment, cityId);
        return cityRepository.findById(cityId)
                .map(city -> {
                    final Comment commentToBeCreated = commentDtoConverter.from(comment);
                    commentToBeCreated.setCity(city);
                    final Comment createdComment = commentRepository.save(commentToBeCreated);
                    log.info("Comment successfully added to city and given id {}.", createdComment.getId());
                    return createdComment;
                })
                .map(commentDtoConverter::from);
    }

    /**
     * Gets dto converter.
     *
     * @return Check {@link DtoConverter}.
     * @see CityService#getDtoConverter()
     */
    @Override
    public DtoConverter<CityDto, City> getDtoConverter() {
        return cityDtoConverter;
    }

    /**
     * Gets message source.
     *
     * @return Message source.
     * @see CityService#getMessageSource()
     */
    @Override
    public MessageSource getMessageSource() {
        return messageSource;
    }

    /**
     * Gets searchable repository.
     *
     * @return Check {@link SearchableRepository}.
     * @see CityService#getRepository()
     */
    @Override
    public SearchableRepository<City> getRepository() {
        return cityRepository;
    }
}

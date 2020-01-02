package com.htec.city_management.service;

import com.htec.city_management.repository.entity.City;
import com.htec.city_management.service.dto.CityDto;
import com.htec.city_management.service.dto.CommentDto;
import com.htec.domain_starter.service.SearchableService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * @author Nikola Stanar
 * <p>
 * Service for operations over city.
 */
public interface CityService extends SearchableService<CityDto, City> {

    /**
     * Finds page of comments belonging to city.
     *
     * @param cityId   City id.
     * @param pageable Check pageable.
     * @return Page of comments belonging to city;
     */
    Page<CommentDto> findBy(@NotNull final Long cityId, @NotNull final Pageable pageable);

    /**
     * Adds comment to the city of certain id.
     *
     * @param cityId  Id of the comment.
     * @param comment Comment to be added.
     * @return Optional comment created if city exists.
     */
    Optional<CommentDto> createAndAssignFrom(@NotNull final Long cityId, @NotNull @Valid final CommentDto comment);

}

package com.htec.city_management.controller.impl;

import com.htec.city_management.controller.model.CityModel;
import com.htec.city_management.controller.model.CommentModel;
import com.htec.city_management.controller.model.assembler.impl.CityModelAssembler;
import com.htec.city_management.controller.model.assembler.impl.CommentModelAssembler;
import com.htec.city_management.repository.entity.City;
import com.htec.city_management.service.CityService;
import com.htec.city_management.service.dto.CityDto;
import com.htec.city_management.service.dto.CommentDto;
import com.htec.domain_starter.controller.SearchableController;
import com.htec.domain_starter.controller.exception.NotFoundException;
import com.htec.domain_starter.controller.validation.exception.handler.ControllerAdvice;
import com.htec.domain_starter.service.SearchableService;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static com.htec.domain_starter.common.constants.MessageSourceKeys.RESOURCE_DOES_NOT_EXIST;
import static org.springframework.context.i18n.LocaleContextHolder.getLocale;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Nikola Stanar
 * <p>
 * Controller exposing API operations over city.
 */
@RestController
@RequestMapping("/cities")
@AllArgsConstructor
public class CityController implements SearchableController<CityModel, CityDto, City> {

    /**
     * City service.
     */
    private final CityService cityService;

    /**
     * Model assembler for city.
     */
    private final CityModelAssembler cityModelAssembler;

    /**
     * Model assembler for comment.
     */
    private final CommentModelAssembler commentModelAssembler;

    /**
     * Message source.
     */
    private final MessageSource messageSource;

    /**
     * Finds page of comments belonging to city.
     *
     * @param cityId                  City id.
     * @param pageable                Check {@link Pageable}.
     * @param pagedResourcesAssembler Check {@link PagedResourcesAssembler}.
     * @return Page of comments belonging to city.
     */
    @GetMapping("/{cityId}/comments")
    public ResponseEntity<PagedModel<EntityModel<CommentModel>>> findBy(@PathVariable final Long cityId, final Pageable pageable, final PagedResourcesAssembler<CommentModel> pagedResourcesAssembler) {
        final Page<CommentModel> comments = cityService
                .findBy(cityId, pageable)
                .map(commentModelAssembler::toModel);

        return ResponseEntity.ok(pagedResourcesAssembler.toModel(comments));
    }

    /**
     * Adds comment to existing comment.
     *
     * @param cityId  City id.
     * @param comment Comment to be created and added to city.
     * @return 201 with locations header or 404 with exception message.
     * @see ControllerAdvice#handle(NotFoundException)
     */
    @PostMapping("/{cityId}/comments")
    public ResponseEntity<?> createAndAssignFrom(@PathVariable final Long cityId, @RequestBody final CommentDto comment) {
        return cityService.createAndAssignFrom(cityId, comment)
                .map(commentDto -> {
                    final URI location = linkTo(methodOn
                            (CommentController.class).findBy(commentDto.getId())
                    ).toUri();
                    return ResponseEntity.created(location).build();
                })
                .orElseThrow(() -> {
                    final String message = messageSource.getMessage(RESOURCE_DOES_NOT_EXIST, new Object[]{cityId}, getLocale());
                    return new NotFoundException(message);
                });
    }

    /**
     * Gets searchable service.
     *
     * @return Searchable service.
     * @see SearchableController#getService()
     */
    @Override
    public SearchableService<CityDto, City> getService() {
        return cityService;
    }

    /**
     * Gets model assembler.
     *
     * @return Model assembler.
     * @see SearchableController#getModelAssembler()
     */
    @Override
    public RepresentationModelAssembler<CityDto, CityModel> getModelAssembler() {
        return cityModelAssembler;
    }

    /**
     * Gets message source.
     *
     * @return Message source.
     * @see SearchableController#getMessageSource()
     */
    @Override
    public MessageSource getMessageSource() {
        return messageSource;
    }

}

package com.htec.domain_starter.controller;

import com.htec.domain_starter.controller.validation.exception.handler.ControllerAdvice;
import com.htec.domain_starter.repository.entity.BaseEntity;
import com.htec.domain_starter.service.CrudService;
import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.domain_starter.service.validation.exception.BusinessValidationException;
import com.htec.domain_starter.service.validation.exception.NotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.net.URI;

import static com.htec.domain_starter.common.constants.MessageSourceKeys.RESOURCE_DOES_NOT_EXIST;
import static org.springframework.context.i18n.LocaleContextHolder.getLocale;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Nikola Stanar
 * <p>
 * Crud controller exposing API operations over MODEl.
 */
public interface CrudController<MODEL extends RepresentationModel<MODEL>, DTO extends BaseDto, ENTITY extends BaseEntity> {

    /**
     * Finds page of model entities matching name prefix (if present).
     *
     * @param pageable                Check {@link Pageable}.
     * @param pagedResourcesAssembler Check {@link PagedResourcesAssembler}.
     * @return Page of model entities.
     */
    @GetMapping
    default ResponseEntity<PagedModel<EntityModel<MODEL>>> find(final Pageable pageable, final PagedResourcesAssembler<MODEL> pagedResourcesAssembler) {
        final Page<MODEL> modelEntities = getService()
                .find(pageable)
                .map(getModelAssembler()::toModel);

        return ResponseEntity.ok(pagedResourcesAssembler.toModel(modelEntities));
    }

    /**
     * Finds model entity by id.
     *
     * @param id Id of the model entity,
     * @return Model entity or 404 with exception message.
     * @see ControllerAdvice#handle(NotFoundException)
     */
    @GetMapping("/{id}")
    default ResponseEntity<MODEL> findBy(@PathVariable final Long id) {
        return getService()
                .findById(id)
                .map(getModelAssembler()::toModel)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> {
                    final String message = getMessageSource().getMessage(RESOURCE_DOES_NOT_EXIST, new Object[]{id}, getLocale());
                    return new NotFoundException(message);
                });
    }

    /**
     * Creates model entity from request body.
     *
     * @param dto Request body.
     * @return 201 with location header if successful; otherwise 400 with exception message.
     * @see ControllerAdvice#handle(BusinessValidationException)
     * @see ControllerAdvice#handle(ConstraintViolationException)
     */
    @PostMapping
    default ResponseEntity<Void> createFrom(@RequestBody final DTO dto) {
        final Long id = getService().createFrom(dto).getId();
        final URI location = linkTo(methodOn(getClass()).findBy(id)).toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Updates model entity with given id from the request body content.
     *
     * @param id  Id of the model entity.
     * @param dto Request body.
     * @return 204 if successful; otherwise one of (404, 400) with exception message.
     * @see ControllerAdvice#handle(BusinessValidationException)
     * @see ControllerAdvice#handle(ConstraintViolationException)
     */
    @PutMapping("/{id}")
    default ResponseEntity<?> updateFrom(@PathVariable final Long id, @RequestBody final DTO dto) {
        getService().updateFrom(id, dto);
        return ResponseEntity.noContent().build();
    }

    /**
     * Deletes model entity having given id.
     *
     * @param id Id of the model entity.
     * @return 204 if successful; otherwise 404 with exception message.
     * @see ControllerAdvice#handle(NotFoundException)
     */
    @DeleteMapping("/{id}")
    default ResponseEntity<?> deleteBy(@PathVariable final Long id) {
        getService().deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Gets crud service.
     *
     * @return Crud service.
     */
    CrudService<DTO, ENTITY> getService();

    /**
     * Gets model assembler.
     *
     * @return Model assembler.
     */
    RepresentationModelAssembler<DTO, MODEL> getModelAssembler();

    /**
     * Gets message source.
     *
     * @return Message source.
     */
    MessageSource getMessageSource();

}

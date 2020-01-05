package com.htec.domain_starter.controller;

import com.htec.domain_starter.controller.validation.exception.handler.ControllerAdvice;
import com.htec.domain_starter.repository.entity.BaseEntity;
import com.htec.domain_starter.service.CrudService;
import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.domain_starter.service.validation.exception.BusinessValidationException;
import com.htec.domain_starter.service.validation.exception.NotFoundException;
import com.htec.domain_starter.service.validation.util.ExceptionUtil;
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

import static com.htec.domain_starter.common.constants.MessageSourceKeys.RESOURCE_DOES_NOT_EXIST;

/**
 * @author Nikola Stanar
 * <p>
 * Crud controller exposing API operations over MODEl.
 */
public interface CrudController<M extends RepresentationModel<M>, D extends BaseDto, E extends BaseEntity> {

    /**
     * Finds page of model entities matching name prefix (if present).
     *
     * @param pageable                Check {@link Pageable}.
     * @param pagedResourcesAssembler Check {@link PagedResourcesAssembler}.
     * @return Page of model entities.
     */
    @GetMapping
    default ResponseEntity<PagedModel<EntityModel<M>>> find(final Pageable pageable, final PagedResourcesAssembler<M> pagedResourcesAssembler) {
        final Page<M> modelEntities = getService()
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
    default ResponseEntity<M> findBy(@PathVariable final Long id) {
        return getService()
                .findById(id)
                .map(getModelAssembler()::toModel)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> getExceptionUtil().createNotFoundExceptionFrom(RESOURCE_DOES_NOT_EXIST, new Object[]{id}));
    }

    /**
     * Creates model entity from request body.
     *
     * @param d Request body.
     * @return 200 with model in response body; otherwise 400 with exception message.
     * @see ControllerAdvice#handle(BusinessValidationException)
     * @see ControllerAdvice#handle(ConstraintViolationException)
     */
    @PostMapping
    default ResponseEntity<M> createFrom(@RequestBody final D d) {
        final D createdDto = getService().createFrom(d);
        final M model = getModelAssembler().toModel(createdDto);
        return ResponseEntity.ok(model);
    }

    /**
     * Updates model entity with given id from the request body content.
     *
     * @param id Id of the model entity.
     * @param d  Request body.
     * @return 200 with model in response body; otherwise one of (404, 400) with exception message.
     * @see ControllerAdvice#handle(BusinessValidationException)
     * @see ControllerAdvice#handle(ConstraintViolationException)
     * @see ControllerAdvice#handle(NotFoundException)
     */
    @PutMapping("/{id}")
    default ResponseEntity<M> updateFrom(@PathVariable final Long id, @RequestBody final D d) {
        final D updatedDto = getService().updateFrom(id, d);
        final M model = getModelAssembler().toModel(updatedDto);
        return ResponseEntity.ok(model);
    }

    /**
     * Deletes model entity having given id.
     *
     * @param id Id of the model entity.
     * @return 204 if successful; otherwise 404 with exception message.
     * @see ControllerAdvice#handle(NotFoundException)
     */
    @DeleteMapping("/{id}")
    default ResponseEntity<Void> deleteBy(@PathVariable final Long id) {
        getService().deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Gets crud service.
     *
     * @return Crud service.
     */
    CrudService<D, E> getService();

    /**
     * Gets model assembler.
     *
     * @return Model assembler.
     */
    RepresentationModelAssembler<D, M> getModelAssembler();

    /**
     * Gets exception util.
     *
     * @return Exception util.
     */
    default ExceptionUtil getExceptionUtil() {
        return getService().getExceptionUtil();
    }

}

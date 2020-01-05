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
                .orElseThrow(() -> getExceptionUtil().createNotFoundExceptionFrom(RESOURCE_DOES_NOT_EXIST, new Object[]{id}));
    }

    /**
     * Creates model entity from request body.
     *
     * @param dto Request body.
     * @return 200 with model in request body; otherwise 400 with exception message.
     * @see ControllerAdvice#handle(BusinessValidationException)
     * @see ControllerAdvice#handle(ConstraintViolationException)
     */
    @PostMapping
    default ResponseEntity<MODEL> createFrom(@RequestBody final DTO dto) {
        final DTO createdDto = getService().createFrom(dto);
        final MODEL model = getModelAssembler().toModel(createdDto);
        return ResponseEntity.ok(model);
    }

    /**
     * Updates model entity with given id from the request body content.
     *
     * @param id  Id of the model entity.
     * @param dto Request body.
     * @return 200 with model in request body; otherwise one of (404, 400) with exception message.
     * @see ControllerAdvice#handle(BusinessValidationException)
     * @see ControllerAdvice#handle(ConstraintViolationException)
     * @see ControllerAdvice#handle(NotFoundException)
     */
    @PutMapping("/{id}")
    default ResponseEntity<?> updateFrom(@PathVariable final Long id, @RequestBody final DTO dto) {
        final DTO updatedDto = getService().updateFrom(id, dto);
        return ResponseEntity.ok(updatedDto);
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
     * Gets exception util.
     *
     * @return Exception util.
     */
    default ExceptionUtil getExceptionUtil() {
        return getService().getExceptionUtil();
    }

}

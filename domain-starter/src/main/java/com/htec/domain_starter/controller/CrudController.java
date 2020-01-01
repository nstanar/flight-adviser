package com.htec.domain_starter.controller;

import com.htec.domain_starter.controller.exception.NotFoundException;
import com.htec.domain_starter.repository.entity.BaseEntity;
import com.htec.domain_starter.service.CrudService;
import com.htec.domain_starter.service.dto.BaseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Nikola Stanar
 * <p>
 * Crud controller exposing API operations over MODEl.
 */
public interface CrudController<MODEL extends RepresentationModel<MODEL>, DTO extends BaseDto, ENTITY extends BaseEntity> {

    /**
     * Message template for non-existing resource.
     */
    String RESOURCE_DOES_NOT_EXIST_MESSAGE_TEMPLATE = "Resource of id %d does not exist.";

    /**
     * Finds page of model entities matching name prefix (if present).
     *
     * @param pageable                Check {@link Pageable}.
     * @param pagedResourcesAssembler Check {@link PagedResourcesAssembler}.
     * @return Page of model entities.
     */
    @GetMapping
    default ResponseEntity<PagedModel<EntityModel<MODEL>>> findAll(final Pageable pageable, final PagedResourcesAssembler<MODEL> pagedResourcesAssembler) {
        final Page<MODEL> modelEntities = getService()
                .findAll(pageable)
                .map(getModelAssembler()::toModel);

        return ResponseEntity.ok(pagedResourcesAssembler.toModel(modelEntities));
    }

    /**
     * Finds model entity by id.
     *
     * @param id Id of the model entity,
     * @return Model entity.
     */
    @GetMapping("/{id}")
    default ResponseEntity<MODEL> findBy(@PathVariable final Long id) {
        return getService()
                .findBy(id)
                .map(getModelAssembler()::toModel)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> {
                    final String message = String.format(RESOURCE_DOES_NOT_EXIST_MESSAGE_TEMPLATE, id);
                    return new NotFoundException(message);
                });
    }

    /**
     * Creates model entity from request body.
     *
     * @param dto Request body.
     * @return 201 with location header.
     */
    @PostMapping
    default ResponseEntity<Void> create(@RequestBody final DTO dto) {
        final Long id = getService().create(dto);
        final URI location = linkTo(methodOn(getClass()).findBy(id)).toUri();
        return ResponseEntity.created(location).build();
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

}

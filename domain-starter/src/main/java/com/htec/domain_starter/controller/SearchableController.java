package com.htec.domain_starter.controller;

import com.htec.domain_starter.repository.BaseEntity;
import com.htec.domain_starter.service.SearchableService;
import com.htec.domain_starter.service.dto.BaseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Nikola Stanar
 * <p>
 * Searchable controller expsoing API operations over MODEL.
 */
public interface SearchableController<M extends RepresentationModel<M>, D extends BaseDto, E extends BaseEntity> extends PagingAndSortingController<M, D, E> {

    /**
     * Finds page of model entities matching name filter (if present).
     *
     * @param nameFilter              Model name property value filter.
     * @param pageable                Check {@link Pageable}.
     * @param pagedResourcesAssembler Check {@link PagedResourcesAssembler}.
     * @return Page of model entities.
     */
    @GetMapping("/search")
    default ResponseEntity<PagedModel<EntityModel<M>>> findBy(@RequestParam(required = false) final String nameFilter, final Pageable pageable, final PagedResourcesAssembler<M> pagedResourcesAssembler) {
        final Page<M> modelEntities = getService()
                .findBy(nameFilter, pageable)
                .map(getModelAssembler()::toModel);

        return ResponseEntity.ok(pagedResourcesAssembler.toModel(modelEntities));

    }

    /**
     * Gets searchable service.
     *
     * @return Searchable service.
     */
    @Override
    SearchableService<D, E> getService();

}

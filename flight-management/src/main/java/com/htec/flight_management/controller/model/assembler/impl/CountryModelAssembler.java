package com.htec.flight_management.controller.model.assembler.impl;

import com.htec.flight_management.controller.impl.CountryController;
import com.htec.flight_management.controller.model.CountryModel;
import com.htec.flight_management.service.dto.CountryDto;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static com.htec.flight_management.common.constants.HypermediaRelNames.HAVING_CITIES;
import static com.htec.domain_starter.controller.util.ControllerLinkBuilder.buildFrom;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Nikola Stanar
 * <p>
 * Representation model asembler for {@link CountryModel}.
 */
@Component
@NoArgsConstructor
public class CountryModelAssembler implements RepresentationModelAssembler<CountryDto, CountryModel> {

    /**
     * Assembles country model from dto.
     *
     * @param dto Dto from which model is going to be assembled.
     * @return Assembled country model.
     */
    @Override
    public CountryModel toModel(final CountryDto dto) {
        final CountryModel model = CountryModel
                .builder()
                .name(dto.getName())
                .build();

        final Long countryId = dto.getId();

        // Add self link.
        final Link selfLink = linkTo(methodOn(
                CountryController.class).findBy(countryId)
        ).withSelfRel();

        model.add(selfLink);

        // Add cities link.
        final WebMvcLinkBuilder citiesLinkBuilder = linkTo(methodOn
                (CountryController.class).findCitiesBy(countryId, Pageable.unpaged(), new PagedResourcesAssembler<>(null, null))
        );
        final Link citiesLink = buildFrom(HAVING_CITIES, citiesLinkBuilder, PageRequest.of(0, 20));

        model.add(citiesLink);

        return model;
    }
}

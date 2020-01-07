package com.htec.flight_management.controller.model.assembler.impl;

import com.htec.flight_management.common.constants.HypermediaRelNames;
import com.htec.flight_management.controller.impl.CityController;
import com.htec.flight_management.controller.model.CityModel;
import com.htec.flight_management.service.dto.CityDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static com.htec.domain_starter.controller.util.ControllerLinkBuilder.buildFrom;
import static com.htec.flight_management.common.constants.HypermediaRelNames.HAVING_COMMENTS;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Nikola Stanar
 * <p>
 * Representation model assembler for {@link CityDto} to {@link CityModel}.
 */
@Component
@AllArgsConstructor
public class CityModelAssembler implements RepresentationModelAssembler<CityDto, CityModel> {

    /**
     * Country model assembler.
     */
    private final CountryModelAssembler countryModelAssembler;

    /**
     * Assembles city model from dto.
     *
     * @param dto City dto used in assembly process.
     * @return Assembled city model.
     */
    @Override
    public CityModel toModel(final CityDto dto) {
        final CityModel cityModel = CityModel.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .country(countryModelAssembler.toModel(dto.getCountry()))
                .build();

        final Long id = dto.getId();

        //Add self link.
        final Link selfLink = linkTo(methodOn
                (CityController.class).findBy(id)
        ).withSelfRel();

        cityModel.add(selfLink);

        // Add comments link.
        final WebMvcLinkBuilder commentsLinkBuilder = linkTo(methodOn
                (CityController.class).findCommentsBy(id, Pageable.unpaged(), new PagedResourcesAssembler<>(null, null))
        );
        final Link commentsLink = buildFrom(HAVING_COMMENTS, commentsLinkBuilder, PageRequest.of(0, 20));

        cityModel.add(commentsLink);

        // Add airports link.
        final WebMvcLinkBuilder airportsLinkBuilder = linkTo(methodOn
                (CityController.class).findAirportsBy(id, Pageable.unpaged(), new PagedResourcesAssembler<>(null, null))
        );
        final Link airportsLink = buildFrom(HypermediaRelNames.HAVING_AIRPORTS, airportsLinkBuilder, PageRequest.of(0, 20));

        cityModel.add(airportsLink);

        return cityModel;
    }
}

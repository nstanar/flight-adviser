package com.htec.flight_management.controller.model.assembler.impl;

import com.htec.flight_management.controller.impl.CityController;
import com.htec.flight_management.controller.impl.CountryController;
import com.htec.flight_management.controller.model.CityModel;
import com.htec.flight_management.service.dto.CityDto;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static com.htec.flight_management.common.constants.HypermediaRelNames.HAVING_COMMENTS;
import static com.htec.flight_management.common.constants.HypermediaRelNames.OF_COUNTRY;
import static com.htec.domain_starter.controller.util.ControllerLinkBuilder.buildFrom;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Nikola Stanar
 * <p>
 * Representation model assembler for {@link CityDto} to {@link CityModel}.
 */
@Component
@NoArgsConstructor
public class CityModelAssembler implements RepresentationModelAssembler<CityDto, CityModel> {

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
                // Setting only country name here, because it will be needed in search results. Full country info provided in link below.
                .countryName(dto.getCountryName())
                .build();

        //Add self link.
        final Link selfLink = linkTo(methodOn
                (CityController.class).findBy(dto.getId())
        ).withSelfRel();

        cityModel.add(selfLink);

        final Long countryId = dto.getCountryId();

        // Add country link.
        final Link countryLink = linkTo(methodOn
                (CountryController.class).findBy(countryId)
        ).withRel(OF_COUNTRY);

        cityModel.add(countryLink);

        // Add comments link.
        final WebMvcLinkBuilder commentsLinkBuilder = linkTo(methodOn
                (CityController.class).findBy(countryId, Pageable.unpaged(), new PagedResourcesAssembler<>(null, null))
        );
        final Link commentsLink = buildFrom(HAVING_COMMENTS, commentsLinkBuilder, PageRequest.of(0, 20));

        cityModel.add(commentsLink);

        return cityModel;
    }
}

package com.htec.city_management.controller.model.assembler.impl;

import com.htec.city_management.controller.impl.CityController;
import com.htec.city_management.controller.impl.CountryController;
import com.htec.city_management.controller.model.CityModel;
import com.htec.city_management.service.dto.CityDto;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static com.htec.city_management.common.constants.HypermediaRelNames.OF_COUNTRY_REL_NAME;
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

        // Add country link because of possible properties in future.

        final Link countryLink = linkTo(methodOn
                (CountryController.class).findBy(dto.getCountryId())
        ).withRel(OF_COUNTRY_REL_NAME);

        cityModel.add(countryLink);

        return cityModel;
    }
}

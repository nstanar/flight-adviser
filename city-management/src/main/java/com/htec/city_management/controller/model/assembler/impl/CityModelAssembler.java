package com.htec.city_management.controller.model.assembler.impl;

import com.htec.city_management.controller.model.CityModel;
import com.htec.city_management.service.dto.CityDto;
import org.springframework.hateoas.server.RepresentationModelAssembler;

/**
 * @author Nikola Stanar
 */
public class CityModelAssembler implements RepresentationModelAssembler<CityDto, CityModel> {

    @Override
    public CityModel toModel(final CityDto entity) {
        return null;
    }
}

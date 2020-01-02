package com.htec.city_management.controller.impl;

import com.htec.city_management.controller.model.CityModel;
import com.htec.city_management.controller.model.assembler.impl.CityModelAssembler;
import com.htec.city_management.repository.entity.City;
import com.htec.city_management.service.CityService;
import com.htec.city_management.service.dto.CityDto;
import com.htec.domain_starter.controller.SearchableController;
import com.htec.domain_starter.service.SearchableService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nikola Stanar
 * <p>
 * Controller exposing API operations over city.
 */
@RestController
@RequestMapping("/cities")
@AllArgsConstructor
public class CityController implements SearchableController<CityModel, CityDto, City> {

    /**
     * City service.
     */
    private final CityService cityService;

    /**
     * Model assembler for city.
     */
    private final CityModelAssembler cityModelAssembler;


    //TODO: add comments relation here

    /**
     * Gets searchable service.
     *
     * @return Searchable service.
     * @see SearchableController#getService()
     */
    @Override
    public SearchableService<CityDto, City> getService() {
        return cityService;
    }

    /**
     * Gets model assembler.
     *
     * @return Model assembler.
     * @see SearchableController#getModelAssembler()
     */
    @Override
    public RepresentationModelAssembler<CityDto, CityModel> getModelAssembler() {
        return cityModelAssembler;
    }

}

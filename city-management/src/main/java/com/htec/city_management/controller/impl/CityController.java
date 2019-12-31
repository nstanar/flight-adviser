package com.htec.city_management.controller.impl;

import com.htec.city_management.controller.model.CityModel;
import com.htec.city_management.controller.model.assembler.impl.CityModelAssembler;
import com.htec.city_management.service.CityService;
import com.htec.domain_starter.controller.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.htec.city_management.common.constants.MessageSourceKeys.CITY_DOES_NOT_EXIST;

/**
 * @author Nikola Stanar
 * <p>
 * Controller exposing API operations over city.
 */
@RestController
@RequestMapping("/cities")
@AllArgsConstructor
public class CityController {

    /**
     * City service.
     */
    private final CityService cityService;

    /**
     * Model assembler for city.
     */
    private final CityModelAssembler cityModelAssembler;

    /**
     * Message source.
     */
    private final MessageSource messageSource;

    @GetMapping("/{id}")
    public ResponseEntity<CityModel> findBy(@PathVariable final Long id) {
        return cityService.findBy(id)
                .map(cityModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> {
                    final String exceptionMessage = messageSource.getMessage(CITY_DOES_NOT_EXIST, new Object[]{id}, LocaleContextHolder.getLocale());
                    return new NotFoundException(exceptionMessage);
                });
    }

}

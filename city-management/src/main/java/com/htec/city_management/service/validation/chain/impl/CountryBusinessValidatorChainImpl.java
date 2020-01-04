package com.htec.city_management.service.validation.chain.impl;

import com.htec.city_management.service.dto.CountryDto;
import com.htec.city_management.service.validation.validator.impl.CountryNameUniquenessValidator;
import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.domain_starter.service.validation.chain.BusinessValidatorChain;
import com.htec.domain_starter.service.validation.marker.Create;
import com.htec.domain_starter.service.validation.validator.BusinessValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Nikola Stanar
 * <p>
 * Country business validator chain.
 */
@Component
@AllArgsConstructor
public class CountryBusinessValidatorChainImpl implements BusinessValidatorChain<CountryDto> {

    /**
     * Validates uniqueness of country name.
     */
    private final CountryNameUniquenessValidator countryNameUniquenessValidator;

    /**
     * Decision cache.
     */
    protected final Map<Class<?>, List<BusinessValidator<CountryDto>>> decisionCache = new HashMap<>();

    /**
     * Decouples validators.
     */
    @PostConstruct
    protected void decoupleValidators() {
        decisionCache.put(Create.class, Collections.singletonList(countryNameUniquenessValidator));
    }

    /**
     * Performs group validation based on action trigger.
     *
     * @param actionTrigger Marker interface to trigger specific validation flow.
     * @param dto           DTO to be validated.
     * @see BusinessValidatorChain#validateFor(Class, BaseDto)
     */
    @Override
    public void validateFor(final @NotNull Class<?> actionTrigger, @NotNull final CountryDto dto) {
        decisionCache
                .get(actionTrigger)
                .forEach(businessValidator -> businessValidator.validate(dto));
    }

}

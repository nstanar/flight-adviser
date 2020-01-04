package com.htec.city_management.service.validation.chain.impl;

import com.htec.city_management.service.dto.CountryDto;
import com.htec.city_management.service.validation.CountryNameUniquenessValidator;
import com.htec.domain_starter.service.validation.chain.BusinessValidatorChain;
import com.htec.domain_starter.service.validation.marker.Create;
import com.htec.domain_starter.service.validation.validator.BusinessValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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
     * Gets decision cache.
     *
     * @return Decision cache.
     * @see BusinessValidatorChain#getDecisionCache()
     */
    @Override
    public Map<Class<?>, List<BusinessValidator<CountryDto>>> getDecisionCache() {
        return decisionCache;
    }
}

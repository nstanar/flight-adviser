package com.htec.flight_management.service.validation.chain.impl;

import com.htec.domain_starter.service.validation.chain.BusinessValidatorChain;
import com.htec.domain_starter.service.validation.marker.Create;
import com.htec.domain_starter.service.validation.marker.Update;
import com.htec.domain_starter.service.validation.validator.BusinessValidator;
import com.htec.flight_management.service.dto.CityDto;
import com.htec.flight_management.service.validation.CityNameUniquenessValidator;
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
 * Business validator chain for city.
 */
@Component
@AllArgsConstructor
public class CityBusinessValidatorChainImpl implements BusinessValidatorChain<CityDto, Long> {

    /**
     * Validates uniqueness of country name.
     */
    private final CityNameUniquenessValidator cityNameUniquenessValidator;

    /**
     * Decision cache.
     */
    protected final Map<Class<?>, List<BusinessValidator<CityDto, Long>>> decisionCache = new HashMap<>();

    /**
     * Decouples validators.
     */
    @PostConstruct
    protected void decoupleValidators() {
        decisionCache.put(Create.class, Collections.singletonList(cityNameUniquenessValidator));
        decisionCache.put(Update.class, Collections.singletonList(cityNameUniquenessValidator));
    }

    /**
     * Gets decision cache.
     *
     * @return Decision cache.
     * @see BusinessValidatorChain#getDecisionCache()
     */
    @Override
    public Map<Class<?>, List<BusinessValidator<CityDto, Long>>> getDecisionCache() {
        return decisionCache;
    }

}

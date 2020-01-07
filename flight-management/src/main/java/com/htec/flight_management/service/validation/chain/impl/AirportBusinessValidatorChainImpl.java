package com.htec.flight_management.service.validation.chain.impl;

import com.htec.domain_starter.service.validation.chain.BusinessValidatorChain;
import com.htec.domain_starter.service.validation.marker.Create;
import com.htec.domain_starter.service.validation.marker.Update;
import com.htec.domain_starter.service.validation.validator.BusinessValidator;
import com.htec.flight_management.service.dto.AirportDto;
import com.htec.flight_management.service.validation.AirportCodeUniquenessValidator;
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
 * Business validator chain for airport.
 * @see BusinessValidatorChain
 */
@Component
@AllArgsConstructor
public class AirportBusinessValidatorChainImpl implements BusinessValidatorChain<AirportDto> {


    /**
     * Validates uniqueness of airport code.
     */
    private final AirportCodeUniquenessValidator airportCodeUniquenessValidator;

    /**
     * Decision cache.
     */
    protected final Map<Class<?>, List<BusinessValidator<AirportDto>>> decisionCache = new HashMap<>();

    /**
     * Decouples validators.
     */
    @PostConstruct
    protected void decoupleValidators() {
        decisionCache.put(Create.class, Collections.singletonList(airportCodeUniquenessValidator));
        decisionCache.put(Update.class, Collections.singletonList(airportCodeUniquenessValidator));
    }

    /**
     * Gets decision cache.
     *
     * @return Decision cache.
     * @see BusinessValidatorChain#getDecisionCache()
     */
    @Override
    public Map<Class<?>, List<BusinessValidator<AirportDto>>> getDecisionCache() {
        return decisionCache;
    }

}

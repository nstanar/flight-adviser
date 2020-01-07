package com.htec.flight_management.service.validation.chain.impl;

import com.htec.domain_starter.service.validation.chain.BusinessValidatorChain;
import com.htec.domain_starter.service.validation.marker.Create;
import com.htec.domain_starter.service.validation.marker.Update;
import com.htec.domain_starter.service.validation.validator.BusinessValidator;
import com.htec.flight_management.service.dto.AirlineDto;
import com.htec.flight_management.service.validation.AirlineCodeUniquenessValidator;
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
 * Airline business validator chain.
 */
@Component
@AllArgsConstructor
public class AirlineBusinessValidatorChainImpl implements BusinessValidatorChain<AirlineDto, Long> {


    /**
     * Validates uniqueness of airline code.
     */
    private final AirlineCodeUniquenessValidator airlineCodeUniquenessValidator;

    /**
     * Decision cache.
     */
    protected final Map<Class<?>, List<BusinessValidator<AirlineDto, Long>>> decisionCache = new HashMap<>();

    /**
     * Decouples validators.
     */
    @PostConstruct
    protected void decoupleValidators() {
        decisionCache.put(Create.class, Collections.singletonList(airlineCodeUniquenessValidator));
        decisionCache.put(Update.class, Collections.singletonList(airlineCodeUniquenessValidator));
    }

    /**
     * Gets decision cache.
     *
     * @return Decision cache.
     * @see BusinessValidatorChain#getDecisionCache()
     */
    @Override
    public Map<Class<?>, List<BusinessValidator<AirlineDto, Long>>> getDecisionCache() {
        return decisionCache;
    }

}

package com.htec.flight_management.service.validation.chain.impl;

import com.htec.domain_starter.service.validation.chain.BusinessValidatorChain;
import com.htec.domain_starter.service.validation.marker.Create;
import com.htec.domain_starter.service.validation.marker.Update;
import com.htec.domain_starter.service.validation.validator.BusinessValidator;
import com.htec.flight_management.service.dto.FlightDto;
import com.htec.flight_management.service.validation.SourceAndDestinationValidator;
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
 * Business validator chain for flight.
 * @see BusinessValidatorChain
 */
@Component
@AllArgsConstructor
public class FlightBusinessValidatorChainImpl implements BusinessValidatorChain<FlightDto> {

    /**
     * Validates that source and destination are not the same.
     */
    private final SourceAndDestinationValidator sourceAndDestinationValidator;

    /**
     * Decision cache.
     */
    protected final Map<Class<?>, List<BusinessValidator<FlightDto>>> decisionCache = new HashMap<>();

    /**
     * Decouples validators.
     */
    @PostConstruct
    protected void decoupleValidators() {
        decisionCache.put(Create.class, Collections.singletonList(sourceAndDestinationValidator));
        decisionCache.put(Update.class, Collections.singletonList(sourceAndDestinationValidator));
    }

    /**
     * Gets decision cache.
     *
     * @return Decision cache.
     * @see BusinessValidatorChain#getDecisionCache()
     */
    @Override
    public Map<Class<?>, List<BusinessValidator<FlightDto>>> getDecisionCache() {
        return decisionCache;
    }

}

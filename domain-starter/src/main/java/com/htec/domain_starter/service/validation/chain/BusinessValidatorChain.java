package com.htec.domain_starter.service.validation.chain;

import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.domain_starter.service.validation.validator.BusinessValidator;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author Nikola Stanar
 * <p>
 * Validator chain for {@link BusinessValidator}.
 */
@FunctionalInterface
@Validated
public interface BusinessValidatorChain<DTO extends BaseDto> {

    /**
     * Performs group validation based on action trigger.
     *
     * @param actionTrigger Marker interface to trigger specific validation flow.
     * @param dto           DTO to be validated.
     */
    void validateFor(@NotNull final Class<?> actionTrigger, @NotNull DTO dto);

}

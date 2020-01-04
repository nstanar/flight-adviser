package com.htec.domain_starter.service.validation.chain;

import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.domain_starter.service.validation.validator.BusinessValidator;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

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
    default void validateFor(final @NotNull Class<?> actionTrigger, @NotNull final DTO dto) {
        final List<BusinessValidator<DTO>> businessValidators = getDecisionCache().get(actionTrigger);
        if (CollectionUtils.isNotEmpty(businessValidators)) {
            businessValidators.forEach(businessValidator -> businessValidator.validate(dto));
        }
    }

    /**
     * Gets decision cache.
     *
     * @return Decision cache.
     */
    Map<Class<?>, List<BusinessValidator<DTO>>> getDecisionCache();

}

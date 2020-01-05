package com.htec.domain_starter.service.validation.util;

import com.htec.domain_starter.repository.entity.BaseEntity;
import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.domain_starter.service.validation.validator.BusinessValidator;

/**
 * @author Nikola Stanar
 * <p>
 * Uniquenes validator helper.
 * @see BusinessValidator#validate(BaseDto)
 */
public interface UniquenessValidatorTemplate<DTO extends BaseDto, ENTITY extends BaseEntity> extends BusinessValidator<DTO> {

    /**
     * Template method for checking uniqueness.
     *
     * @param id                Id.
     * @param entity            Entity.
     * @param messageKey        Message key.
     * @param messageParameters Message parameters.
     */
    default void validate(final Long id, final ENTITY entity, final String messageKey, final Object[] messageParameters) {
        boolean alreadyExists = false;

        if (entity != null) {
            if (id != null) {
                // Existing dto.
                if (!entity.getId().equals(id)) {
                    // Existing dto with different id and same unique property value.
                    alreadyExists = true;
                }
            } else {
                // Completely new dto with existing value of unique property.
                alreadyExists = true;
            }
        }

        if (alreadyExists) {
            throw getExceptionUtil().createBusinessValidationExceptionFrom(messageKey, messageParameters);
        }

    }

    /**
     * Gets exception util.
     *
     * @return Exception util.
     */
    ExceptionUtil getExceptionUtil();
}

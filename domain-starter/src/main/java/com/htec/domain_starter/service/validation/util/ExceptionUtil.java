package com.htec.domain_starter.service.validation.util;

import com.htec.domain_starter.service.validation.exception.BusinessValidationException;
import com.htec.domain_starter.service.validation.exception.NotFoundException;

/**
 * @author Nikola Stanar
 * <p>
 * Exception utility class used for validation purposes.
 */
public interface ExceptionUtil {

    /**
     * Creates {@link BusinessValidationException} with message constructed from message key and message parameters.
     *
     * @param messageKey        Message key used to obtain message template.
     * @param messageParameters Message parameters used to fill message template.
     * @return Check {@link BusinessValidationException}.
     */
    BusinessValidationException createBusinessValidationExceptionFrom(final String messageKey, final Object[] messageParameters);

    /**
     * Creates {@link NotFoundException} with message constructed from message key and message parameters.
     *
     * @param messageKey        Message key used to obtain message template.
     * @param messageParameters Message parameters used to fill message template.
     * @return Check {@link NotFoundException}.
     */
    NotFoundException createNotFoundExceptionFrom(final String messageKey, final Object[] messageParameters);
}

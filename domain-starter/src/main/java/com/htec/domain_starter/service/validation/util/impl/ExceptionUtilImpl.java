package com.htec.domain_starter.service.validation.util.impl;

import com.htec.domain_starter.service.validation.exception.BusinessValidationException;
import com.htec.domain_starter.service.validation.exception.NotFoundException;
import com.htec.domain_starter.service.validation.util.ExceptionUtil;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

/**
 * @author Nikola Stanar
 * <p>
 * Exception utility class used for validation purposes.
 * @see ExceptionUtilImpl
 */
@Component
@AllArgsConstructor
public class ExceptionUtilImpl implements ExceptionUtil {

    /**
     * Message source.
     */
    private final MessageSource messageSource;

    /**
     * Creates {@link BusinessValidationException} with message constructed from message key and message parameters.
     *
     * @param messageKey        Message key used to obtain message template.
     * @param messageParameters Message parameters used to fill message template.
     * @return Check {@link BusinessValidationException}.
     * @see ExceptionUtil#createBusinessValidationExceptionFrom(String, Object[])
     */
    @Override
    public BusinessValidationException createBusinessValidationExceptionFrom(final String messageKey, final Object[] messageParameters) {
        final String message = messageSource.getMessage(
                messageKey,
                messageParameters != null ? messageParameters : new Object[]{},
                getLocale());
        return new BusinessValidationException(message);
    }

    /**
     * Creates {@link NotFoundException} with message constructed from message key and message parameters.
     *
     * @param messageKey        Message key used to obtain message template.
     * @param messageParameters Message parameters used to fill message template.
     * @return Check {@link BusinessValidationException}.
     * @see ExceptionUtil#createNotFoundExceptionFrom(String, Object[]) (String, Object[])
     */
    @Override
    public NotFoundException createNotFoundExceptionFrom(final String messageKey, final Object[] messageParameters) {
        final String message = messageSource.getMessage(
                messageKey,
                messageParameters != null ? messageParameters : new Object[]{},
                getLocale());
        return new NotFoundException(message);
    }

}

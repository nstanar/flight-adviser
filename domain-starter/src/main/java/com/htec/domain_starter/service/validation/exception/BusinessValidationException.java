package com.htec.domain_starter.service.validation.exception;

/**
 * @author Nikola Stanar
 * <p>
 * Business validation exception.
 */
public class BusinessValidationException extends RuntimeException {

    /**
     * Constructor.
     *
     * @param message Exception message.
     */
    public BusinessValidationException(final String message) {
        super(message);
    }

}

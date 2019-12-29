package com.htec.domain_starter.controller.exception;

/**
 * @author Nikola Stanar
 * <p>
 * Not found exception.
 */
public class NotFoundException extends RuntimeException {

    /**
     * Constructor.
     *
     * @param message Exception message.
     */
    public NotFoundException(final String message) {
        super(message);
    }
}


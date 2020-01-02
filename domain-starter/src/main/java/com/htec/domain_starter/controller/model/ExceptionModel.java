package com.htec.domain_starter.controller.model;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Nikola Stanar
 * <p>
 * Used to present exceptions.
 */
@Getter
@Builder
public class ExceptionModel {

    /**
     * Message describing exception.
     */
    private final String message;
}

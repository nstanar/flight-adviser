package com.htec.domain_starter.controller.representation_model;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Nikola Stanar
 * <p>
 * Used to present exceptions.
 */
@Getter
@Builder
public class ExceptionRepresentationModel {

    /**
     * Message describing exception.
     */
    private final String message;
}

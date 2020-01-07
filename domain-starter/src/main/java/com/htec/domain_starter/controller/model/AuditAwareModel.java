package com.htec.domain_starter.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

/**
 * @author Nikola Stanar
 * <p>
 * Representation model for audit content.
 */
@AllArgsConstructor
@Getter
@Setter
public abstract class AuditAwareModel<T extends AuditAwareModel<T>> extends RepresentationModel<T> {

    /**
     * Designates creation date of the resource.
     */
    private final Long createdDate;

    /**
     * Designates last modified date of the resource.
     */
    private final Long modifiedDate;

    /**
     * Designates who created the resource.
     */
    private final String createdBy;

    /**
     * Designates who did last modification of the resource.
     */
    private final String modifiedBy;

}

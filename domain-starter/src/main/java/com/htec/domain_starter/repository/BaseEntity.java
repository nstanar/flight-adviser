package com.htec.domain_starter.repository;

import java.io.Serializable;

/**
 * @author Nikola Stanar
 * <p>
 * Generic entity that is to be extended by concrete entities.
 */
public interface BaseEntity<ID> extends Serializable {

    /**
     * Getter for id.
     *
     * @return Id.
     */
    ID getId();

}

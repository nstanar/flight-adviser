package com.htec.domain_starter.repository;

/**
 * @author Nikola Stanar
 * <p>
 * Entity class representing audit content.
 */
public interface AuditedEntity<ID> extends BaseEntity<ID> {

    /**
     * Getter for created date.
     *
     * @return Created date.
     */
    Long getCreatedDate();

    /**
     * Getter for last modified date.
     *
     * @return Last modified date.
     */
    Long getModifiedDate();

    /**
     * Getter for created by.
     *
     * @return Created by.
     */
    String getCreatedBy();

    /**
     * Getter for modified by.
     *
     * @return Modified by.
     */
    String getModifiedBy();

    /**
     * Setter for created date.
     *
     * @param createdDate Created date.
     */
    void setCreatedDate(final Long createdDate);

    /**
     * Setter for modified date.
     *
     * @param modifiedDate Modified date.
     */
    void setModifiedDate(final Long modifiedDate);

    /**
     * Setter for created by.
     *
     * @param createdBy Created by.
     */
    void setCreatedBy(final String createdBy);

    /**
     * Setter for modified by.
     *
     * @param modifiedBy Modified by.
     */
    void setModifiedBy(final String modifiedBy);
}



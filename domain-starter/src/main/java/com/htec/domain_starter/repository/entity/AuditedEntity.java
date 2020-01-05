package com.htec.domain_starter.repository.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

/**
 * @author Nikola Stanar
 * <p>
 * Entity class representing audit content.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class AuditedEntity extends BaseEntity {

    /**
     * Designates creation date of the record.
     */
    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private Long createdDate;

    /**
     * Designates last modified date of the record.
     */
    @Column(name = "modified_date")
    @LastModifiedDate
    private Long modifiedDate;

    /**
     * Designates who created the record.
     */
    @Column(name = "created_by", nullable = false, updatable = false)
    @CreatedBy
    private String createdBy;

    /**
     * Designates who did last modification of the record.
     */
    @Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy;

}

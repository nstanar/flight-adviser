package com.htec.domain_starter.repository.entity.jpa;

import com.htec.domain_starter.repository.AuditedEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

/**
 * @author Nikola Stanar
 * <p>
 * Entity class representing jpa audit content.
 */
@MappedSuperclass
@Data
public abstract class JpaAuditedEntity<ID> implements AuditedEntity<ID> {

    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(value = AccessLevel.PRIVATE)
    private ID id;

    /**
     * Designates creation date of the record.
     */
    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private Long createdDate;

    /**
     * Designates last modified date of the record.
     */
    @LastModifiedDate
    @Column(name = "modified_date")
    private Long modifiedDate;

    /**
     * Designates who created the record.
     */
    @CreatedBy
    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;

    /**
     * Designates who did last modification of the record.
     */
    @LastModifiedBy
    @Column(name = "modified_by")
    private String modifiedBy;

}
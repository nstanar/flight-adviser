package com.htec.domain_starter.repository.entity.neo4j;

import com.htec.domain_starter.repository.AuditedEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * @author Nikola Stanar
 * <p>
 * Neo4j audited entity class.
 */
@Getter
@Setter
public abstract class Neo4jAuditedEntity implements AuditedEntity {

    /**
     * Id.
     */
    @Id
    @GeneratedValue
    @Setter(value = AccessLevel.PRIVATE)
    private Long id;

    /**
     * Designates creation date of the record.
     */
    @CreatedDate
    private Long createdDate;

    /**
     * Designates last modified date of the record.
     */
    @LastModifiedDate
    private Long modifiedDate;

    /**
     * Designates who created the record.
     */
    @CreatedBy
    private String createdBy;

    /**
     * Designates who did last modification of the record.
     */
    @LastModifiedBy
    private String modifiedBy;

}

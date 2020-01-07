package com.htec.domain_starter.repository.entity.neo4j;

import com.htec.domain_starter.repository.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * @author Nikola Stanar
 * <p>
 * Neo4j base entity class.
 */
@NodeEntity
@Getter
@Setter(AccessLevel.PRIVATE)
public abstract class Neo4jBaseEntity<ID> implements BaseEntity<ID> {

    /**
     * Neo4j requires annotations on field.
     */
    @Id
    @GeneratedValue
    private ID id;

}

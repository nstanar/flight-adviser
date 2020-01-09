package com.htec.flight_management.repository.entity;

import com.htec.domain_starter.repository.entity.neo4j.Neo4jAuditedEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import static org.neo4j.ogm.annotation.Relationship.INCOMING;

/**
 * @author Nikola Stanar
 * <p>
 * Entity class representing comment.
 */
@NodeEntity
@Getter
@Setter
@NoArgsConstructor
public class Comment extends Neo4jAuditedEntity {

    /**
     * Title of the comment;
     */
    private String title;

    /**
     * Description of the comment.
     */
    private String description;

    /**
     * City comment belongs to.
     */
    @Relationship(type = "HAS_COMMENT", direction = INCOMING)
    private City city;

}

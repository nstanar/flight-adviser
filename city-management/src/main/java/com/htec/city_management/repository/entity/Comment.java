package com.htec.city_management.repository.entity;

import com.htec.domain_starter.repository.entity.AuditedEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Nikola Stanar
 * <p>
 * Entity class representing comment.
 */
@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Comment extends AuditedEntity {

    /**
     * Title of the comment;
     */
    @Column(nullable = false)
    private String title;

    /**
     * Description of the comment.
     */
    @Column(nullable = false)
    private String description;

    /**
     * City comment belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;
}

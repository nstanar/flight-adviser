package com.htec.city_management.repository.entity;

import com.htec.domain_starter.repository.entity.AuditedEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author Nikola Stanar
 * <p>
 * Entity class representing comment.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table
@NoArgsConstructor
@Data
public class Comment extends AuditedEntity {

    /**
     * Title of the comment;
     */
    @Column(nullable = false, length = 50)
    private String title;

    /**
     * Description of the comment.
     */
    @Column(nullable = false, length = 500)
    private String description;

    /**
     * City comment belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;
}

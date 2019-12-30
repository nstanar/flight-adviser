package com.htec.city_management.repository.entity;

import com.htec.domain_starter.repository.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
public class Comment extends BaseEntity {

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

    //TODO: add auditing

}

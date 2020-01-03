package com.htec.domain_starter.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author Nikola Stanar
 * <p>
 * Generic entity that is to be extended by concrete entities.
 */
@MappedSuperclass
@Getter
@NoArgsConstructor
public abstract class BaseEntity implements Serializable {

    /**
     * Database entry ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}

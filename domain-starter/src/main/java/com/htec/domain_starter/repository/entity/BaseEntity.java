package com.htec.domain_starter.repository.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

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
@Data
@Setter(value = AccessLevel.PRIVATE)
public abstract class BaseEntity implements Serializable {

    /**
     * Database entry ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}

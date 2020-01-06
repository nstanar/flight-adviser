package com.htec.domain_starter.repository.entity.jpa;

import com.htec.domain_starter.repository.BaseEntity;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Nikola Stanar
 * <p>
 * Jpa base entity class.
 */
@MappedSuperclass
@Data
public class JpaBaseEntity implements BaseEntity {

    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}

package com.htec.domain_starter.repository.entity.jpa;

import com.htec.domain_starter.repository.BaseEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

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
@Setter(value = AccessLevel.PRIVATE)
public class JpaBaseEntity<ID> implements BaseEntity<ID> {

    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;
}

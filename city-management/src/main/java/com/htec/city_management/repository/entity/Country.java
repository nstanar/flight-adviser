package com.htec.city_management.repository.entity;

import com.htec.domain_starter.repository.entity.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Nikola Stanar
 * <p>
 * Entity class representing country.
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "country_name_unique_constraint", columnNames = {"name"})})
@NoArgsConstructor
@Data
public class Country extends BaseEntity {

    /**
     * Country name.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Collection of cities residing in country.
     */
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<City> cities;

}

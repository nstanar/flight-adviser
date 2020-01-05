package com.htec.city_management.repository.entity;

import com.htec.domain_starter.repository.entity.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Nikola Stanar
 * <p>
 * Entity class representing city.
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "country_city_unique_constraint", columnNames = {"country_id", "name"})})
@NoArgsConstructor
@Data
public class City extends BaseEntity {

    /**
     * Name of the city.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Country in which city resides in.
     */
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    /**
     * Description of the city.
     */
    @Column(nullable = false, length = 1000)
    private String description;

    /**
     * Comments for the city.
     */
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments;

}

package com.htec.city_management.repository.entity;

import com.htec.domain_starter.repository.entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Nikola Stanar
 * <p>
 * Entity class representing city.
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "country_city_unique_constraint", columnNames = {"country_id", "name"})})
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
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
    @Column(nullable = false)
    private String description;

    /**
     * Comments for the city.
     */
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments;

}

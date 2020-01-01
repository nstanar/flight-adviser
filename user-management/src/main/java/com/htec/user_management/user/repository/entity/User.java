package com.htec.user_management.user.repository.entity;

import com.htec.domain_starter.repository.entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Nikola Stanar
 * <p>
 * Entity class representing user.
 */
@Entity
@Table(name = "sys_user",
        uniqueConstraints = {@UniqueConstraint(name = "sys_user_username", columnNames = {"username"})}
)
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"password"})
public class User extends BaseEntity {

    /**
     * User's first name.
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * User's last name.
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /**
     * User's username.
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * User's password.
     */
    @Column(name = "value_sec", nullable = false)
    private String password;

}

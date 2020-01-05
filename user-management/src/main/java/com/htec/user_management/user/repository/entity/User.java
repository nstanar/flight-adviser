package com.htec.user_management.user.repository.entity;

import com.htec.domain_starter.repository.entity.AuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

/**
 * @author Nikola Stanar
 * <p>
 * Entity class representing user.
 */
@Entity
@Table(name = "fa_user", uniqueConstraints = @UniqueConstraint(name = "sys_user_username", columnNames = "username"))
@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = "password")
@ToString(exclude = "password")
public class User extends AuditedEntity implements UserDetails {

    /**
     * User's first name.
     */
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    /**
     * User's last name.
     */
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    /**
     * User's username.
     */
    @Column(nullable = false, length = 50)
    private String username;

    /**
     * User's password.
     */
    @Column(name = "value_sec", nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(
            name = "fa_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = @UniqueConstraint(name = "fa_user_role_unique_constraint", columnNames = {"user_id", "role_id"}))
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

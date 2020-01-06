package com.htec.user_management.user.repository.entity;

import com.htec.domain_starter.repository.entity.jpa.JpaAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Nikola Stanar
 * <p>
 * Entity class representing user.
 */
@Entity
@Table(name = "fa_user", uniqueConstraints = @UniqueConstraint(name = "sys_user_username", columnNames = "username"))
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = "password")
@ToString(exclude = "password")
public class User extends JpaAuditedEntity implements UserDetails {

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
     * User's value sec.
     */
    @Column(name = "value_sec", nullable = false)
    private String password;

    /**
     * User role.
     */
    @ManyToMany
    @JoinTable(
            name = "fa_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = @UniqueConstraint(name = "fa_user_role_unique_constraint", columnNames = {"user_id", "role_id"}))
    private Set<Role> roles = new HashSet<>();

    /**
     * Getter for value sec.
     *
     * @return Value sec.
     */

    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Gets authorities.
     *
     * @return Authorities.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    /**
     * Boolean indicator method of non expired account.
     *
     * @return True if not expired.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Boolean indicator method of non locked account.
     *
     * @return True if non locked.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Boolean indicator method of non expired credentials.
     *
     * @return True if non expired.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Boolean indicator method of enabled account.
     *
     * @return True if enabled.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}

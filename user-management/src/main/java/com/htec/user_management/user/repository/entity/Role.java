package com.htec.user_management.user.repository.entity;

import com.htec.domain_starter.repository.entity.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Nikola Stanar
 * <p>
 * Entity class representing role.
 */
@Entity
@Table(name = "fa_role", uniqueConstraints = @UniqueConstraint(name = "fa_role_name_unique_constraint", columnNames = "name"))
@NoArgsConstructor
@Data
public class Role extends BaseEntity implements GrantedAuthority {

    /**
     * Name of the role.
     */
    @Column(nullable = false, length = 30)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @Override
    public String getAuthority() {
        return name;
    }
}

package com.htec.user_management.user.repository.entity;

import com.htec.domain_starter.repository.entity.jpa.JpaBaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Nikola Stanar
 * <p>
 * Entity class representing role.
 */
@Entity
@Table(name = "fa_role", uniqueConstraints = @UniqueConstraint(name = "fa_role_name_unique_constraint", columnNames = "name"))
@NoArgsConstructor
@Data
public class Role extends JpaBaseEntity<Long> implements GrantedAuthority {

    /**
     * Name of the role.
     */
    @Column(nullable = false, length = 30)
    private String name;

    /**
     * Getter for authority.
     *
     * @return Authority.
     */
    @Override
    public String getAuthority() {
        return name;
    }

}

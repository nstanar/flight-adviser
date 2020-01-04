package com.htec.domain_starter.repository;

import com.htec.domain_starter.repository.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Nikola Stanar
 * <p>
 * Searchable repository.
 */
@NoRepositoryBean
//TODO: if time left, make it generic with criteria API so it can be used for any field.
public interface SearchableRepository<ENTITY extends BaseEntity> extends JpaRepository<ENTITY, Long> {

    /**
     * Finds page of entities matching name filter.
     * <p>
     *
     * @param namePrefix Name prefix.
     * @return Page of entities matching name filter.
     */
    @Transactional(readOnly = true)
    Page<ENTITY> findByNameStartingWithIgnoreCase(final String namePrefix, final Pageable pageable);

}

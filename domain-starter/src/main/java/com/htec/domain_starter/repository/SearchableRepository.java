package com.htec.domain_starter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Nikola Stanar
 * <p>
 * Searchable repository.
 */
@NoRepositoryBean
//TODO: if time left, make it generic with criteria API so it can be used for any field.
public interface SearchableRepository<E extends BaseEntity> extends PagingAndSortingRepository<E, Long> {

    /**
     * Finds page of entities matching name filter.
     * <p>
     *
     * @param nameFilter Name filter.
     * @return Page of entities matching name filter.
     */
    @Transactional(readOnly = true)
    Page<E> findByNameContainingIgnoreCase(final String nameFilter, final Pageable pageable);

}

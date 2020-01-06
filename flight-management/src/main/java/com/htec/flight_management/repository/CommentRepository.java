package com.htec.flight_management.repository;

import com.htec.flight_management.repository.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Nikola Stanar
 * <p>
 * JPA repository for {@link Comment}.
 */
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {

    /**
     * Finds page of comments by city id.
     *
     * @param cityId   City id.
     * @param pageable Check {@link Pageable}.
     * @return Page of comments.
     */
    @Transactional(readOnly = true)
    Page<Comment> findAllByCityId(final Long cityId, final Pageable pageable);

}

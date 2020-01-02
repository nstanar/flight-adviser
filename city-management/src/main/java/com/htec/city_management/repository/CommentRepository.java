package com.htec.city_management.repository;

import com.htec.city_management.repository.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Nikola Stanar
 * <p>
 * JPA repository for {@link Comment}.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

}

package com.htec.city_management.controller.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

/**
 * @author Nikola Stanar
 * <p>
 * Representation model for comment.
 */
@Getter
@Builder
public class CommentModel extends RepresentationModel<CommentModel> {

    /**
     * Title of the comment;
     */
    private final String title;

    /**
     * Description of the comment.
     */
    private final String description;

}


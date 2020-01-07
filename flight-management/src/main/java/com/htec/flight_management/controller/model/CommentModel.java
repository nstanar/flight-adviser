package com.htec.flight_management.controller.model;

import com.htec.domain_starter.controller.model.AuditAwareModel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.hateoas.server.core.Relation;

/**
 * @author Nikola Stanar
 * <p>
 * Representation model for comment.
 */
@Getter
@Relation(itemRelation = "comment", collectionRelation = "comments")
public class CommentModel extends AuditAwareModel<CommentModel> {

    /**
     * Title of the comment;
     */
    private final String title;

    /**
     * Description of the comment.
     */
    private final String description;

    /**
     * All args constructor.
     *
     * @param title        Title.
     * @param description  Description.
     * @param createdDate  Created date.
     * @param modifiedDate Modified date,
     * @param createdBy    Created by.
     * @param modifiedBy   Modified by.
     */
    @Builder
    public CommentModel(final String title, final String description, final Long createdDate, final Long modifiedDate, final String createdBy, final String modifiedBy) {
        super(createdDate, modifiedDate, createdBy, modifiedBy);
        this.title = title;
        this.description = description;
    }
}


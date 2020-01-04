package com.htec.city_management.service.dto;

import com.htec.domain_starter.service.dto.AuditAwareDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Nikola Stanar
 * <p>
 * Dto class representing comment.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"cityId"})
public class CommentDto extends AuditAwareDto {

    /**
     * Title of the comment;
     */
    @NotBlank
    @Size(min = 3, max = 50)
    private String title;

    /**
     * Description of the comment.
     */
    @NotBlank
    @Size(min = 4, max = 500)
    private String description;

    /**
     * Id of the city comment belongs to.
     */
    private Long cityId;
}

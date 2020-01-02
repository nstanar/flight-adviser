package com.htec.city_management.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.htec.domain_starter.service.dto.BaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class CommentDto extends BaseDto {

    /**
     * Title of the comment;
     */
    @NotBlank
    @Size(min = 5)
    //TODO: comply with varchar lenght
    private String title;

    /**
     * Description of the comment.
     */
    @NotBlank
    @Size(min = 10)
    //TODO: comply with varchar lenght
    private String description;

    /**
     * Id of the city comment belongs to.
     */
    @JsonIgnore
    private Long cityId;
}

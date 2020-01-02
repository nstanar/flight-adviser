package com.htec.domain_starter.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Nikola Stanar
 * <p>
 * Dto class representing audit content.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public abstract class AuditAwareDto extends BaseDto {

    /**
     * Designates creation date of the record.
     */
    @JsonIgnore
    private Long createdDate;

    /**
     * Designates last modified date of the record.
     */
    @JsonIgnore
    private Long modifiedDate;

    /**
     * Designates who created the record.
     */
    @JsonIgnore
    private String createdBy;

    /**
     * Designates who did last modification of the record.
     */
    @JsonIgnore
    private String modifiedBy;

}

package com.htec.domain_starter.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Nikola Stanar
 * <p>
 * Generic DTO that is to be extended by concrete DTOs.
 */
@Data
public abstract class BaseDto implements Serializable {

    /**
     * Id.
     */
    @JsonIgnore
    private Long id;
}

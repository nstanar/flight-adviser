package com.htec.domain_starter.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Nikola Stanar
 * <p>
 * Generic DTO that is to be extended by concrete DTOs.
 */
@Getter
@Setter
public abstract class BaseDto implements Serializable {

    /**
     * Id.
     */
    @JsonIgnore
    private Long id;
}

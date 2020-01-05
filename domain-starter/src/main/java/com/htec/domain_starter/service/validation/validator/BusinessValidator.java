package com.htec.domain_starter.service.validation.validator;

import com.htec.domain_starter.service.dto.BaseDto;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * Validates business logic in dto.
 *
 * @author Nikola Stanar
 */
@FunctionalInterface
@Validated
public interface BusinessValidator<D extends BaseDto> {

    /**
     * Validates dto based on some criteria.
     *
     * @param dto DTO to be validated
     */
    void validate(@NotNull final D dto);

}
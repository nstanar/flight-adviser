package com.htec.city_management.service.dto;

import com.htec.domain_starter.service.dto.BaseDto;
import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * @author Nikola Stanar
 * <p>
 * Dto representing country.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class CountryDto extends BaseDto {

    /**
     * Name of the country.
     */
    @NotBlank
    //TODO: set max size after varchar length is set
    private String name;

}

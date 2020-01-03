package com.htec.city_management.service.dto;

import com.htec.domain_starter.service.dto.BaseDto;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Nikola Stanar
 * <p>
 * Dto representing country.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CountryDto extends BaseDto {

    /**
     * Name of the country.
     */
    @NotBlank
    @Size(min = 1, max = 255)
    private String name;

}

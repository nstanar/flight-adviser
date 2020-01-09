package com.htec.flight_management.service.dto;

import com.htec.domain_starter.service.dto.BaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Nikola Stanar
 * <p>
 * Dto representing country.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CountryDto extends BaseDto {

    /**
     * Name of the country.
     */
    @NotBlank
    @Size(min = 2, max = 255)
    @Pattern(regexp = "^([a-zA-Z\\u0080-\\u024F]+(?:. |-| |'))*[a-zA-Z\\u0080-\\u024F]*$")
    private String name;

}

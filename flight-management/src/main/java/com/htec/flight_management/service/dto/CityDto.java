package com.htec.flight_management.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.htec.domain_starter.service.dto.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Nikola Stanar
 * <p>
 * Dto representing city.
 */
@NoArgsConstructor
@Data
@ToString(exclude = {"countryName"})
public class CityDto extends BaseDto<Long> {

    /**
     * City name.
     */
    @NotBlank
    @Size(min = 1, max = 255)
    @Pattern(regexp = "^([a-zA-Z\\u0080-\\u024F]+(?:. |-| |'))*[a-zA-Z\\u0080-\\u024F]*$")
    private String name;

    /**
     * Description of the city.
     */
    @NotBlank
    @Size(min = 10, max = 1000)
    private String description;

    /**
     * Id of the country city resides in.
     */
    @JsonIgnore
    private Long countryId;

    /**
     * Country city resides in.
     */
    @JsonIgnore
    private CountryDto country;

}

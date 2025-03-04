package com.htec.flight_management.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * Dto representing city.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"countryName"})
public class CityDto extends BaseDto {

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
     * Name of the country city resides in.
     */
    @JsonIgnore
    private String countryName;

}

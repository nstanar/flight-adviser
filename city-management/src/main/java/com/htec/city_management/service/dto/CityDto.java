package com.htec.city_management.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.htec.domain_starter.service.dto.BaseDto;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Nikola Stanar
 * <p>
 * Dto representing city.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"country"})
@EqualsAndHashCode
public class CityDto extends BaseDto {

    /**
     * City name.
     */
    @NotBlank
    @Size(min = 1, max = 255)
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
    private Long countryId;

    /**
     * Name of the country city resides in.
     */
    @JsonIgnore
    private String countryName;

}

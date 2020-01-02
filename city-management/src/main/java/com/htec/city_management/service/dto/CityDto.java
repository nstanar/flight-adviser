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
@ToString(exclude = {"countryId", "countryName"})
@EqualsAndHashCode
public class CityDto extends BaseDto {

    /**
     * City name.
     */
    @NotBlank
    //TODO:adjust this after varchar length is set.
    private String name;

    /**
     * Description of the city.
     */
    @NotBlank
    //TODO: adjust this after varchar lenght is set
    @Size(min = 20)
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

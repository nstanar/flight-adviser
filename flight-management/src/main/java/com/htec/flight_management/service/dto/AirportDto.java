package com.htec.flight_management.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.htec.domain_starter.service.dto.BaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Nikola Stanar
 * <p>
 * Dto class representing airport.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AirportDto extends BaseDto {

    /**
     * Name.
     */
    @NotBlank
    @Size(min = 5, max = 50)
    private String name;

    /**
     * 3-letter IATA code. Null if not assigned/unknown.
     * OR
     * 4-letter ICAO code. Null if not assigned/unknown.
     */
    @Size(min = 3, max = 4)
    private String code;

    /**
     * Id of the city airport belongs to.
     */
    @JsonIgnore
    private Long cityId;

    /**
     * Name of the city airport belongs to.
     */
    @JsonIgnore
    private String cityName;

    /**
     * Name of the country airport belongs to.
     */
    @JsonIgnore
    private String countryName;

}

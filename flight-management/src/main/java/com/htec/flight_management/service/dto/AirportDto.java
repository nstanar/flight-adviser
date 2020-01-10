package com.htec.flight_management.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.htec.domain_starter.service.dto.BaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
     * 3 letter IATA code.
     * Null if not assigned.
     */
    @NotNull
    @Size(min = 3, max = 3)
    @Pattern(regexp = "[a-zA-Z]{3}`")
    private String iataCode;

    /**
     * 4 letter ICAO code.
     * Null if not assigned.
     */
    @NotNull
    @Size(min = 4, max = 4)
    @Pattern(regexp = "[a-zA-Z]{4}")
    private String icaoCode;

    /**
     * Latitude.
     */
    @NotNull
    private Double latitude;

    /**
     * Longitude.
     */
    @NotNull
    private Double longitude;

    /**
     * Id of the city airport belongs to.
     */
    @JsonIgnore
    private Long cityId;

}

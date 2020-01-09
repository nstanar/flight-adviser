package com.htec.flight_management.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.htec.domain_starter.service.dto.BaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Nikola Stanar
 * <p>
 * Dto class representing flight.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class FlightDto extends BaseDto {

    /**
     * Id of the source airport.
     */
    @JsonIgnore
    private Long sourceAirportId;

    /**
     * Id of the destination airport.
     */
    @NotNull
    private Long destinationAirportId;

    /**
     * 2-letter (IATA) or 3-letter (ICAO) code of the airline.
     */
    @NotNull
    @Size(min = 2, max = 3)
    private String airlineCode;

    /**
     * Number of stops.
     * 0 for direct flight.
     */
    @NotNull
    @Min(0)
    private Integer stops;

    /**
     * Price.
     */
    @NotNull
    @DecimalMin("0")
    private Double price;

    /**
     * Distance between source and destination airports.
     */
    @JsonIgnore
    private double distanceInKm;

}

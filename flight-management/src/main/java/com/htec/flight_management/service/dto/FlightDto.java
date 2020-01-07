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
import java.math.BigDecimal;

/**
 * @author Nikola Stanar
 * <p>
 * Dto class representing flight.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class FlightDto extends BaseDto<Long> {

    /**
     * Id of the source airport.
     */
    @NotNull
    private Long sourceAirportId;

    /**
     * Source airport.
     */
    @JsonIgnore
    private AirportDto source;

    /**
     * Id of the destination airport.
     */
    @NotNull
    private Long destinationAirportId;

    /**
     * Destination airport.
     */
    @JsonIgnore
    private AirportDto destination;

    /**
     * Id of the airline.
     */
    @NotNull
    private Long airlineId;

    /**
     * Airline.
     */
    @JsonIgnore
    private AirlineDto airline;

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
    private BigDecimal price;

}

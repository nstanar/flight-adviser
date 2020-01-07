package com.htec.flight_management.service.dto;

import com.htec.domain_starter.service.dto.BaseDto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Nikola Stanar
 * <p>
 * Dto class representing flight.
 */
public class FlightDto extends BaseDto<Long> {

    /**
     * Id of the source airport.
     */
    @NotNull
    private Long sourceAirportId;

    /**
     * Id of the destination airport.
     */
    @NotNull
    private Long destinationAirportId;

    /**
     * Id of the airline.
     */
    @NotNull
    private Long airlineId;

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

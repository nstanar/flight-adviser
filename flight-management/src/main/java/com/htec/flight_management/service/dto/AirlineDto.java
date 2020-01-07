package com.htec.flight_management.service.dto;

import com.htec.domain_starter.service.dto.BaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;

/**
 * @author Nikola Stanar
 * <p>
 * Dto class representing airline.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AirlineDto extends BaseDto<Long> {

    /**
     * 2-letter (IATA) or 3-letter (ICAO) code of the airline.
     */
    @Size(min = 2, max = 3)
    private String code;

}

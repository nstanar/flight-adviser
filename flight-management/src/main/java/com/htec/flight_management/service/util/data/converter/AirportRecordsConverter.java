package com.htec.flight_management.service.util.data.converter;

import com.htec.flight_management.service.util.data.AirportRecord;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * @author Nikola Stanar
 * <p>
 * Converter for aiport records.
 */
@Validated
@FunctionalInterface
public interface AirportRecordsConverter {

    /**
     * Converts from airports data structure
     * to list of airport records.
     *
     * @param airports Airports data structure.
     * @return List of airport records.
     */
    @PreAuthorize("hasRole('ADMIN')")
    List<AirportRecord> from(List<List<String>> airports);

}

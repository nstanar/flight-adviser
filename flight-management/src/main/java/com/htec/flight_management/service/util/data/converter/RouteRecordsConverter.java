package com.htec.flight_management.service.util.data.converter;

import com.htec.flight_management.service.util.data.RouteRecord;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * @author Nikola Stanar
 * <p>
 * Converter for route records.
 */
@Validated
@FunctionalInterface
public interface RouteRecordsConverter {

    /**
     * Converts from routes data structure
     * to list of route records.
     *
     * @param routes Routes data structure.
     * @return List of route records.
     */
    @PreAuthorize("hasRole('ADMIN')")
    List<RouteRecord> from(List<List<String>> routes);

}
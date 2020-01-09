package com.htec.flight_management.service.util;

import com.htec.flight_management.service.util.data.AirportRecord;
import com.htec.flight_management.service.util.data.RouteRecord;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Nikola Stanar
 * <p>
 * Route records second level filter.
 */
@Validated
@FunctionalInterface
public interface RouteRecordsSecondLevelFilter {

    /**
     * Filters route records in second pass.
     *
     * @param routeRecords Route records.
     * @return Filtered route records.
     */
    @PreAuthorize("hasRole('ADMIN')")
    List<RouteRecord> filterFrom(@NotEmpty final List<@NotNull AirportRecord> airportRecords, final List<@NotNull RouteRecord> routeRecords);

}

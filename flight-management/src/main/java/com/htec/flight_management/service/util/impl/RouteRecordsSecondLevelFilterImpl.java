package com.htec.flight_management.service.util.impl;

import com.htec.flight_management.service.util.RouteRecordsSecondLevelFilter;
import com.htec.flight_management.service.util.data.AirportRecord;
import com.htec.flight_management.service.util.data.RouteRecord;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Nikola Stanar
 * <p>
 * Route records second level filter.
 * @see RouteRecordsSecondLevelFilter
 */
@Component
public class RouteRecordsSecondLevelFilterImpl implements RouteRecordsSecondLevelFilter {

    /**
     * Filters route records in second pass.
     *
     * @param routeRecords Route records.
     * @return Filtered route records.
     * @see RouteRecordsSecondLevelFilter#filterFrom(List, List)
     */
    @Override
    public List<RouteRecord> filterFrom(@NotEmpty final List<@NotNull AirportRecord> airportRecords, final List<@NotNull RouteRecord> routeRecords) {

        final List<String> iataCodes = new ArrayList<>();
        final List<String> icaoCodes = new ArrayList<>();
        airportRecords.forEach(airportRecord -> {
            iataCodes.add(airportRecord.getIataCode());
            icaoCodes.add(airportRecord.getIcaoCode());
        });

        return routeRecords
                .parallelStream()
                .filter(routeRecord ->
                        (iataCodes.stream().noneMatch(code -> code.equals(routeRecord.getSourceAirportCode())) ||
                                icaoCodes.stream().noneMatch(code -> code.equals(routeRecord.getSourceAirportCode())))
                                &&
                                (iataCodes.stream().noneMatch(code -> code.equals(routeRecord.getDestinationAirportCode())) ||
                                        icaoCodes.stream().noneMatch(code -> code.equals(routeRecord.getDestinationAirportCode()))))
                .collect(Collectors.toList());
    }

}

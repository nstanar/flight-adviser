package com.htec.flight_management.service.util.data.converter.impl;

import com.htec.flight_management.service.util.data.RouteRecord;
import com.htec.flight_management.service.util.data.converter.RouteRecordsConverter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikola Stanar
 * <p>
 * Converter for route records.
 * @see RouteRecordsConverter
 */
@Component
public class RouteRecordsConverterImpl implements RouteRecordsConverter {

    /**
     * Converts from routes data structure
     * to list of route records.
     *
     * @param routes Routes data structure.
     * @return List of route records.
     * @see RouteRecordsConverter#from(List)
     */
    @Override
    public List<RouteRecord> from(final List<List<String>> routes) {
        final List<RouteRecord> routeRecords = new ArrayList<>();

        for (final List<String> route : routes) {

            final String sourceAirportId = route.get(3);
            final String destinationAirportId = route.get(5);
            final String stops = route.get(7);
            final String price = route.get(9);

            if (NumberUtils.isCreatable(stops) && NumberUtils.isCreatable(price)) {

                final RouteRecord routeRecord = RouteRecord.builder()
                        .airlineCode(route.get(0))
                        .sourceAirportCode(route.get(2))
                        .destinationAirportCode(route.get(4))
                        .stops(Integer.parseInt(stops))
                        .price(Double.parseDouble(price))
                        .build();

                if (StringUtils.isNotBlank(routeRecord.getSourceAirportCode()) &&
                        StringUtils.isNotBlank(routeRecord.getDestinationAirportCode()) &&
                        StringUtils.isNotBlank(routeRecord.getAirlineCode()) &&
                        routeRecord.getPrice() > 0 &&
                        routeRecord.getStops() >= 0
                ) {

                    routeRecords.add(routeRecord);

                }
            }

        }

        return routeRecords;
    }
}

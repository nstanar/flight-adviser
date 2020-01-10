package com.htec.flight_management.service.util.data.converter.impl;

import com.htec.flight_management.service.util.data.AirportRecord;
import com.htec.flight_management.service.util.data.converter.AirportRecordsConverter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Nikola Stanar
 * <p>
 * Converter for aiport records.
 * @see AirportRecordsConverter
 */
@Component
public class AirportRecordsConverterImpl implements AirportRecordsConverter {

    /**
     * Iata pattern.
     */
    private final Pattern iataPattern = Pattern.compile("[a-zA-Z]{3}");

    /**
     * Icao pattern.
     */
    private final Pattern icaoPattern = Pattern.compile("[a-zA-Z]{4}");

    /**
     * Converts from airports data structure
     * to list of airport records.
     *
     * @param airports Airports data structure.
     * @return List of airport records.
     * @see AirportRecordsConverter#from(List)
     */
    @Override
    public List<AirportRecord> from(final List<List<String>> airports) {
        final List<AirportRecord> airportRecords = new ArrayList<>();

        for (final List<String> airport : airports) {

            final String iataCode = airport.get(4);
            final String icaoCode = airport.get(5);

            final String latitude = airport.get(6);
            final String longitude = airport.get(7);

            if (NumberUtils.isCreatable(latitude) &&
                    NumberUtils.isCreatable(longitude)) {

                final AirportRecord airportRecord = AirportRecord.builder()
                        .name(airport.get(1))
                        .cityName(airport.get(2))
                        .countryName(airport.get(3))
                        .iataCode(iataCode)
                        .icaoCode(icaoCode)
                        .latitude(Double.parseDouble(latitude))
                        .longitude(Double.parseDouble(longitude))
                        .build();

                if (StringUtils.isNotBlank(airportRecord.getIataCode()) && iataPattern.matcher(airportRecord.getIataCode()).matches() &&
                        StringUtils.isNotBlank(airportRecord.getIcaoCode()) && icaoPattern.matcher(airportRecord.getIcaoCode()).matches()
                        &&
                        StringUtils.isNotBlank(airportRecord.getName()) &&
                        StringUtils.isNotBlank(airportRecord.getCityName()) &&
                        StringUtils.isNotBlank(airportRecord.getCountryName())) {

                    airportRecords.add(airportRecord);
                }
            }

        }

        return airportRecords;
    }

}

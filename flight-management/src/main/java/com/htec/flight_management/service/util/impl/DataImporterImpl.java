package com.htec.flight_management.service.util.impl;

import com.htec.flight_management.repository.AirportRepository;
import com.htec.flight_management.repository.CityRepository;
import com.htec.flight_management.repository.CountryRepository;
import com.htec.flight_management.repository.FlightRepository;
import com.htec.flight_management.repository.entity.Airport;
import com.htec.flight_management.repository.entity.City;
import com.htec.flight_management.repository.entity.Country;
import com.htec.flight_management.repository.entity.Flight;
import com.htec.flight_management.service.util.DataImporter;
import com.htec.flight_management.service.util.RouteRecordsSecondLevelFilter;
import com.htec.flight_management.service.util.data.AirportRecord;
import com.htec.flight_management.service.util.data.RouteRecord;
import com.htec.flight_management.service.util.data.converter.AirportRecordsConverter;
import com.htec.flight_management.service.util.data.converter.RouteRecordsConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import java.util.*;

/**
 * @author Nikola Stanar
 * <p>
 * Imports airports and routes data.
 * @see DataImporter
 */
@Service
@Slf4j
@AllArgsConstructor
public class DataImporterImpl implements DataImporter {

    /**
     * Airport records converter.
     */
    private final AirportRecordsConverter airportRecordsConverter;

    /**
     * Route records converter.
     */
    private final RouteRecordsConverter routeRecordsConverter;

    /**
     * Route records second level filter.
     */
    private final RouteRecordsSecondLevelFilter routeRecordsSecondLevelFilter;

    /**
     * Country repository.
     */
    private final CountryRepository countryRepository;

    /**
     * City repository.
     */
    private final CityRepository cityRepository;

    /**
     * Airport repository.
     */
    private final AirportRepository airportRepository;

    /**
     * Flight repository.
     */
    private final FlightRepository flightRepository;

    /**
     * Imports data from airports and routes structures.
     *
     * @param airports Airports structure.
     * @param routes   Routes structure.
     * @see DataImporter#importFrom(List, List)
     */
    @Override
    public void importFrom(final @NotEmpty List<@NotEmpty List<String>> airports, @NotEmpty final List<@NotEmpty List<String>> routes) {
        final Long startTime = System.currentTimeMillis();
        log.info("Importing {} airports and {} routes.", airports.size(), routes.size());
        final List<AirportRecord> airportRecords = airportRecordsConverter.from(airports);
        final List<RouteRecord> filteredRouteRecords = routeRecordsSecondLevelFilter.filterFrom(airportRecords, routeRecordsConverter.from(routes));

        // Create countries, cities, airports.
        final Set<Country> countries = new HashSet<>();
        final Set<City> cities = new HashSet<>();
        final Set<Airport> airportSet = new HashSet<>();

        airportRecords.forEach(airportRecord -> {
            final Country country = new Country();
            country.setName(airportRecord.getCountryName());
            countries.add(country);

            final City city = new City();
            city.setName(airportRecord.getCityName());
            country.getCities().add(city);
            cities.add(city);

            final Airport airport = new Airport();
            airport.setName(airportRecord.getName());
            final String iataCode = airportRecord.getIataCode();
            airport.setIataCode(iataCode);
            final String icaoCode = airportRecord.getIcaoCode();
            airport.setIcaoCode(icaoCode);
            airport.setLatitude(airportRecord.getLatitude());
            airport.setLongitude(airportRecord.getLongitude());
            city.getAirports().add(airport);
            airportSet.add(airport);
        });

        final Iterable<Airport> createdAirports = airportRepository.saveAll(airportSet);
        cityRepository.saveAll(cities);
        countryRepository.saveAll(countries);

        final Map<String, Airport> airportsByIataCode = new HashMap<>();
        final Map<String, Airport> airportsByIcaoCode = new HashMap<>();
        createdAirports.forEach(airport -> {
            airportsByIataCode.put(airport.getIataCode(), airport);
            airportsByIcaoCode.put(airport.getIcaoCode(), airport);
        });

        // Create flights.
        final Set<Flight> flights = new HashSet<>();
        filteredRouteRecords.forEach(routeRecord -> {

            // Source.
            final Airport sourceByIataCode = airportsByIataCode.get(routeRecord.getSourceAirportCode());
            final Airport sourceByIcaoCode = airportsByIcaoCode.get(routeRecord.getSourceAirportCode());

            // Destination.
            final Airport destinationByIataCode = airportsByIataCode.get(routeRecord.getDestinationAirportCode());
            final Airport destinationByIcaoCode = airportsByIcaoCode.get(routeRecord.getDestinationAirportCode());

            final boolean canBeRouted = (sourceByIataCode != null || sourceByIcaoCode != null) &&
                    (destinationByIataCode != null || destinationByIcaoCode != null);

            if (canBeRouted) {
                final Flight flight = new Flight();
                flight.setSource(sourceByIataCode != null ? sourceByIataCode : sourceByIcaoCode);
                flight.setDestination(destinationByIataCode != null ? destinationByIataCode : destinationByIcaoCode);
                flight.setAirlineCode(routeRecord.getAirlineCode());
                flight.setStops(routeRecord.getStops());
                flight.setPrice(routeRecord.getPrice());

                flight.getSource().setOut(flight);
                flight.getDestination().setInc(flight);

                flights.add(flight);

                // Workaround because saving set of flights didn't work.
                flightRepository.createWith(flight.getSource().getId(), flight.getDestination().getId(), flight.getAirlineCode(), flight.getStops(), flight.getPrice());
            }

        });

        //flightRepository.saveAll(flights);

        final Long endTime = System.currentTimeMillis();
        log.info("After filtering imported {} airports and {} routes in {} ms.", airportRecords.size(), flights.size(), endTime - startTime);
    }

}

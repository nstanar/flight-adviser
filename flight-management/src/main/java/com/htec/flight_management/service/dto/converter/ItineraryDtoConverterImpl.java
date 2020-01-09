package com.htec.flight_management.service.dto.converter;

import com.htec.flight_management.repository.FlightRepository;
import com.htec.flight_management.repository.entity.Flight;
import com.htec.flight_management.service.dto.AirportShortestPathRecordDto;
import com.htec.flight_management.service.dto.ItineraryDto;
import com.htec.flight_management.service.dto.RouteDto;
import lombok.AllArgsConstructor;
import one.util.streamex.StreamEx;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Nikola Stanar
 * Itinerary dto converter.
 * @see ItineraryDtoConverter
 */
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class ItineraryDtoConverterImpl implements ItineraryDtoConverter {

    /**
     * Flight repository.
     */
    private final FlightRepository flightRepository;

    /**
     * Flight dto converter.
     */
    private final FlightDtoConverter flightDtoConverter;

    /**
     * Converts from iterable of shortest path records to itinerary dto.
     *
     * @param shortestPath Iterable of shortest path records.
     * @return Itinerary dto..
     * @see ItineraryDtoConverter#from(List)
     */
    @Override
    public ItineraryDto from(@NotEmpty @Size(min = 2) final List<AirportShortestPathRecordDto> shortestPath) {
        final RouteDto[] routes = StreamEx.of(shortestPath).pairMap((record1, record2) -> {
            final RouteDto route = new RouteDto();
            route.setSource(record1.getName() + " (" + record1.getIataCode() + ", " + record1.getIcaoCode() + "), " + record1.getCity() + ", " + record1.getCountry());
            route.setDestination(record2.getName() + " (" + record2.getIataCode() + ", " + record2.getIcaoCode() + "), " + record2.getCity() + ", " + record2.getCountry());
            final Flight flight = flightRepository.findBySourceIdAndDestinationId(record1.getId(), record2.getId());
            route.setPrice(flight.getPrice());
            route.setDistanceInKm(flightDtoConverter.from(flight).getDistanceInKm());
            return route;
        }).toArray(new RouteDto[]{});

        return ItineraryDto.builder()
                .routes(Arrays.asList(routes))
                .totalPrice(shortestPath.get(0).getTotalCost())
                .totalDistanceInKm(Stream.of(routes).mapToDouble(RouteDto::getDistanceInKm).sum())
                .build();
    }

}

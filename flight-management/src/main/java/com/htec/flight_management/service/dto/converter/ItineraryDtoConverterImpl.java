package com.htec.flight_management.service.dto.converter;

import com.htec.domain_starter.service.validation.util.ExceptionUtil;
import com.htec.flight_management.repository.AirportRepository;
import com.htec.flight_management.repository.entity.Airport;
import com.htec.flight_management.service.client.dto.CheapestRouteDto;
import com.htec.flight_management.service.dto.ItineraryDto;
import com.htec.flight_management.service.dto.RouteDto;
import com.htec.flight_management.service.util.DistanceCalculator;
import lombok.AllArgsConstructor;
import org.gavaghan.geodesy.GlobalPosition;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.htec.domain_starter.common.constants.MessageSourceKeys.RESOURCE_DOES_NOT_EXIST;

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
     * Airport repository.
     */
    private final AirportRepository airportRepository;

    /**
     * Exception util.
     */
    private final ExceptionUtil exceptionUtil;

    /**
     * Distance calculator.
     */
    private final DistanceCalculator distanceCalculator;

    /**
     * Converts from iterable of shortest path records to itinerary dto.
     *
     * @param cheapestFlight Iterable of shortest path records.
     * @return Itinerary dto..
     * @see ItineraryDtoConverter#from(List)
     */
    @Override
    public ItineraryDto from(@NotEmpty final List<@NotNull CheapestRouteDto> cheapestFlight) {
        final List<RouteDto> routes = cheapestFlight.stream()
                .map(cheapestRouteDto -> {
                    final RouteDto routeDto = new RouteDto();
                    final Long sourceAirportId = cheapestRouteDto.getSourceAirportId();
                    final Optional<Airport> optionalSource = airportRepository.findById(sourceAirportId);
                    final Airport source;
                    final Airport destination;

                    if (optionalSource.isPresent()) {
                        source = optionalSource.get();
                        routeDto.setSource(source.getName() + " (" + source.getIataCode() + ", " + source.getIcaoCode() + "), " + source.getCity().getName() + ", " + source.getCity().getCountry().getName());
                    } else {
                        throw exceptionUtil.createNotFoundExceptionFrom(RESOURCE_DOES_NOT_EXIST, new Object[]{sourceAirportId});
                    }

                    final Long destinationAirportId = cheapestRouteDto.getDestinationAirportId();
                    final Optional<Airport> optionalDestination = airportRepository.findById(cheapestRouteDto.getDestinationAirportId());
                    if (optionalDestination.isPresent()) {
                        destination = optionalDestination.get();
                        routeDto.setDestination(destination.getName() + " (" + destination.getIataCode() + ", " + destination.getIcaoCode() + "), " + destination.getCity().getName() + ", " + destination.getCity().getCountry().getName());
                    } else {
                        throw exceptionUtil.createNotFoundExceptionFrom(RESOURCE_DOES_NOT_EXIST, new Object[]{destinationAirportId});
                    }

                    routeDto.setAirlineCode(cheapestRouteDto.getAirlineCode());
                    routeDto.setPrice(cheapestRouteDto.getPrice());

                    // Set distance.
                    final GlobalPosition sourcePosition = new GlobalPosition(source.getLatitude(), source.getLongitude(), 0.0);
                    final GlobalPosition destinationPosition = new GlobalPosition(destination.getLatitude(), destination.getLongitude(), 0.0);
                    final double distanceInKm = distanceCalculator.calculateBetween(sourcePosition, destinationPosition);
                    BigDecimal bd = BigDecimal.valueOf(distanceInKm);
                    bd = bd.setScale(2, RoundingMode.HALF_UP);
                    routeDto.setDistanceInKm(bd.doubleValue());

                    return routeDto;
                }).collect(Collectors.toList());

        final double totalDistanceInKm = routes.stream().mapToDouble(route -> {
            BigDecimal bd = BigDecimal.valueOf(route.getDistanceInKm());
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            return bd.doubleValue();
        }).sum();

        final BigDecimal totalPrice = routes.stream().map(RouteDto::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

        return ItineraryDto.builder()
                .routes(routes)
                .totalPrice(totalPrice)
                .totalDistanceInKm(totalDistanceInKm)
                .build();
    }

}

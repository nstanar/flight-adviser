package com.htec.flight_management.service.impl;

import com.htec.domain_starter.service.validation.util.ExceptionUtil;
import com.htec.flight_management.repository.CityRepository;
import com.htec.flight_management.repository.entity.Airport;
import com.htec.flight_management.repository.entity.City;
import com.htec.flight_management.service.ItineraryService;
import com.htec.flight_management.service.client.Neo4jClient;
import com.htec.flight_management.service.client.dto.CheapestRouteDto;
import com.htec.flight_management.service.dto.ItineraryDto;
import com.htec.flight_management.service.dto.converter.ItineraryDtoConverter;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.htec.domain_starter.common.constants.MessageSourceKeys.RESOURCE_DOES_NOT_EXIST;

/**
 * @author Nikola Stanar
 * <p>
 * Service operating on itineraries.
 * @see ItineraryService
 */
@Service
@AllArgsConstructor
public class ItineraryServiceImpl implements ItineraryService {

    /**
     * Neo4j client.
     */
    private final Neo4jClient neo4jClient;

    /**
     * City repository.
     */
    private final CityRepository cityRepository;

    /**
     * Converter for itinerary.
     */
    private final ItineraryDtoConverter itineraryDtoConverter;

    /**
     * Exception util.
     */
    private final ExceptionUtil exceptionUtil;

    /**
     * Finds cheapest itinerary between source and destination city.
     *
     * @param sourceCityId      Id of the source city.
     * @param destinationCityId Id of the destination city.
     * @return Optional cheapest itinerary.
     * @see ItineraryService#findCheapestBetweenCities(Long, Long)
     */
    @Override
    public Optional<ItineraryDto> findCheapestBetweenCities(final @NotNull Long sourceCityId, final @NotNull Long destinationCityId) {
        Optional<ItineraryDto> result = Optional.empty();

        final Optional<City> optionalSourceCity = cityRepository.findById(sourceCityId);
        if (optionalSourceCity.isEmpty()) {
            throw exceptionUtil.createNotFoundExceptionFrom(RESOURCE_DOES_NOT_EXIST, new Object[]{sourceCityId});
        }

        final Optional<City> optionalDestinationCity = cityRepository.findById(destinationCityId);
        if (optionalDestinationCity.isEmpty()) {
            throw exceptionUtil.createNotFoundExceptionFrom(RESOURCE_DOES_NOT_EXIST, new Object[]{destinationCityId});
        }

        final City sourceCity = optionalSourceCity.get();
        final City destinationCity = optionalDestinationCity.get();

        final long[] sourceAirportIds = sourceCity.
                getAirports()
                .stream()
                .map(Airport::getId)
                .mapToLong(x -> x)
                .toArray();

        final long[] destinationAirportIds = destinationCity.
                getAirports()
                .stream()
                .map(Airport::getId)
                .mapToLong(x -> x)
                .toArray();


        final Map<String, List<CheapestRouteDto>> allOptionsForGivenCities = new HashMap<>();

        for (final long sourceAirportId : sourceAirportIds) {
            for (final long destinationAirportId : destinationAirportIds) {
                final List<CheapestRouteDto> cheapestPath = neo4jClient.findCheapestPathBetween(sourceAirportId, destinationAirportId);
                if (CollectionUtils.isNotEmpty(cheapestPath)) {
                    final String key = sourceAirportId + "" + destinationAirportId;
                    allOptionsForGivenCities.put(key, IterableUtils.toList(cheapestPath));
                }
            }
        }

        final Optional<String> optionalBest = allOptionsForGivenCities
                .keySet()
                .stream()
                .min((key1, key2) -> {
                    final List<CheapestRouteDto> option1 = allOptionsForGivenCities.get(key1);
                    final List<CheapestRouteDto> option2 = allOptionsForGivenCities.get(key2);
                    final BigDecimal totalPrice1 = option1.stream().map(CheapestRouteDto::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
                    final BigDecimal totalPrice2 = option2.stream().map(CheapestRouteDto::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
                    return totalPrice1.compareTo(totalPrice2);
                });

        if (optionalBest.isPresent()) {
            final List<CheapestRouteDto> bestOption = allOptionsForGivenCities.get(optionalBest.get());
            final ItineraryDto itinerary = itineraryDtoConverter.from(bestOption);
            result = Optional.of(itinerary);
        }

        return result;
    }


}

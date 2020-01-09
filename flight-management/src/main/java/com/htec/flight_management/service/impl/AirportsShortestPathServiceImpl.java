package com.htec.flight_management.service.impl;

import com.htec.flight_management.service.AirportsShortestPathService;
import com.htec.flight_management.service.dto.AirportShortestPathRecordDto;
import lombok.AllArgsConstructor;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.neo4j.ogm.transaction.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Nikola Stanar
 * <p>
 * Shoortest path repository for airports.
 * @see AirportsShortestPathService
 */
@Service
@Transactional
@AllArgsConstructor
public class AirportsShortestPathServiceImpl implements AirportsShortestPathService {

    /**
     * Session factory.
     */
    private final SessionFactory sessionFactory;

    /**
     * Query template.
     */
    private static final String QUERY_TEMPLATE = "MATCH (start:Airport), (end:Airport) " +
            "WHERE id(start)={startId} AND id(end)={endId} " +
            "CALL algo.shortestPath(start, end, \"price\", {prop}) " +
            "YIELD totalCost " +
            "MATCH (airport:Airport) " +
            "MATCH (airport)<-[:HAS_AIRPORT]-(city:City) " +
            "MATCH (city)<-[:HAS_CITY]-(country:Country) " +
            "RETURN id(airport) AS id, airport.name AS name, airport.iataCode AS iataCode, airport.icaoCode AS icaoCode, city.name AS city, country.name AS country, totalCost";

    /**
     * Finds che path between airports.
     *
     * @param sourceAirportId      Start node id.
     * @param destinationAirportId End node id.
     * @return List of {@link AirportShortestPathRecordDto}.
     */
    @Transactional(readOnly = true)
    public List<AirportShortestPathRecordDto> findCheapestBetween(final Long sourceAirportId, final Long destinationAirportId) {
        final List<AirportShortestPathRecordDto> result = new ArrayList<>();

        final Session session = sessionFactory.openSession();
        final Transaction transaction = session.beginTransaction();

        final Map<String, Object> params = new HashMap<>();
        params.put("startId", sourceAirportId);
        params.put("endId", destinationAirportId);
        final Map<String, Object> subParams = new HashMap<>();
        subParams.put("write", true);
        subParams.put("writeProperty", "sssp");
        params.put("prop", subParams);

        final Result query = session.query(QUERY_TEMPLATE, params);
        final Iterable<Map<String, Object>> queryResults = query.queryResults();
        queryResults.forEach(stringObjectMap -> {
            final Long id = (Long) stringObjectMap.get("id");
            final String name = (String) stringObjectMap.get("name");
            final String iataCode = (String) stringObjectMap.get("iataCode");
            final String icaoCode = (String) stringObjectMap.get("icaoCode");
            final String city = (String) stringObjectMap.get("city");
            final String country = (String) stringObjectMap.get("country");
            final double totalPrice = (double) stringObjectMap.get("totalCost");
            result.add(AirportShortestPathRecordDto.builder()
                    .id(id)
                    .name(name)
                    .iataCode(iataCode)
                    .icaoCode(icaoCode)
                    .city(city)
                    .country(country)
                    .totalCost(totalPrice)
                    .build()
            );
        });

        transaction.close();
        session.clear();

        return result;
    }

}

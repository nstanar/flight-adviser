package com.htec.flight_management.controller.impl;

import com.htec.flight_management.service.util.CSVFileParser;
import com.htec.flight_management.service.util.DataImporter;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Nikola Stanar
 * <p>
 * Controller for importing airports and routes.
 */
@RestController
@RequestMapping("/import")
@AllArgsConstructor
public class ImportController {

    /**
     * Csv file parser.
     */
    private final CSVFileParser csvFileParser;

    /**
     * Data importer.
     */
    private final DataImporter dataImporter;

    /**
     * Imports airports and routes.
     *
     * @param airportsFile File containing airports.
     * @param routesFile   File containing routes.
     * @return 204
     */
    @PostMapping
    public ResponseEntity<Void> upload(@RequestParam("airports") final MultipartFile airportsFile, @RequestParam("routes") final MultipartFile routesFile) {

        final List<List<String>> airports = csvFileParser.parseFrom(airportsFile);
        final List<List<String>> routes = csvFileParser.parseFrom(routesFile);

        dataImporter.importFrom(airports, routes);

        return ResponseEntity.noContent().build();
    }

}

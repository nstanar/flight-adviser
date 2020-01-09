package com.htec.flight_management.service.util.impl;

import com.htec.flight_management.service.util.CSVFileParser;
import com.htec.flight_management.service.util.MultipartFileConverter;
import com.opencsv.CSVReader;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Nikola Stanar
 * <p>
 * Parses csv file.
 * @see CSVFileParser
 */
@Component
@Slf4j
@AllArgsConstructor
public class CSVFileParserImpl implements CSVFileParser {

    /**
     * Multipart file converter;
     */
    private final MultipartFileConverter fileConverter;

    /**
     * Parses CSV file.
     *
     * @param multipartFile CSV file.
     * @return Parsed data.
     * @see CSVFileParser#parseFrom(MultipartFile)
     */
    @SneakyThrows
    @Override
    public List<List<String>> parseFrom(@NotNull final MultipartFile multipartFile) {
        final long startTime = System.currentTimeMillis();
        final List<List<String>> records = new ArrayList<>();
        final File file = fileConverter.convert(multipartFile);

        try (final CSVReader csvReader = new CSVReader(new FileReader(file))) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        }

        final long endTime = System.currentTimeMillis();

        log.info("Parsing file {} took {} ms.", multipartFile.getOriginalFilename(), endTime - startTime);

        return records;
    }

}

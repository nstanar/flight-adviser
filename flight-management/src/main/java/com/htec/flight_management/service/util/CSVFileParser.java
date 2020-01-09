package com.htec.flight_management.service.util;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Nikola Stanar
 * <p>
 * Parses csv file.
 */
@Validated
@FunctionalInterface
public interface CSVFileParser {

    /**
     * Parses CSV file.
     *
     * @param multipartFile CSV file.
     * @return Parsed data.
     */
    @PreAuthorize("hasRole('ADMIN')")
    List<List<String>> parseFrom(@NotNull final MultipartFile multipartFile);

}

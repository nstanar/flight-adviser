package com.htec.flight_management.service.util;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author Nikola Stanar
 * <p>
 * Imports airports and routes data.
 */
@Validated
@Transactional
@FunctionalInterface
public interface DataImporter {

    /**
     * Imports data from airports and routes structures.
     *
     * @param airports Airports structure.
     * @param routes   Routes structure.
     */
    @PreAuthorize("hasRole('ADMIN')")
    void importFrom(final @NotEmpty List<@NotEmpty List<String>> airports, @NotEmpty List<@NotEmpty List<String>> routes);
}

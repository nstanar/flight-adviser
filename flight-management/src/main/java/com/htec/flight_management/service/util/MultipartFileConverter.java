package com.htec.flight_management.service.util;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;

/**
 * @author Nikola Stanar
 * <p>
 * Converts multipart file to file.
 */
@Validated
@FunctionalInterface
public interface MultipartFileConverter {

    /**
     * Converts multipart file to file.
     *
     * @param multipartFile Multipart file.
     * @return File.
     */
    @PreAuthorize("hasRole('ADMIN')")
    File convert(@NotNull final MultipartFile multipartFile);

}

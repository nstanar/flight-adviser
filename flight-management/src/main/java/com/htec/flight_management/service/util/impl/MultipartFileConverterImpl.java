package com.htec.flight_management.service.util.impl;

import com.htec.flight_management.service.util.MultipartFileConverter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Objects;

/**
 * @author Nikola Stanar
 * <p>
 * Converts multipart file to file.
 * @see MultipartFileConverter
 */
@Component
public class MultipartFileConverterImpl implements MultipartFileConverter {

    /**
     * Converts multipart file to file.
     *
     * @param multipartFile Multipart file.
     * @return File.
     */
    @SneakyThrows
    @Override
    public File convert(final @NotNull MultipartFile multipartFile) {
        final File convertedFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        final boolean created = convertedFile.createNewFile();
        if (created) {
            try (final FileOutputStream fos = new FileOutputStream(convertedFile)) {
                fos.write(multipartFile.getBytes());
            }
        }

        return convertedFile;
    }

}

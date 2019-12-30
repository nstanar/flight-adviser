package com.htec.domain_starter.controller.validation.exception.handler;

import com.htec.domain_starter.controller.exception.NotFoundException;
import com.htec.domain_starter.controller.representation_model.ExceptionRepresentationModel;
import com.htec.domain_starter.service.validation.exception.BusinessValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * @author Nikola Stanar
 * <p>
 * Controller advice used to handle exceptions.
 */
@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

    /**
     * Handles {@link NotFoundException}.
     *
     * @param exception Check {@link NotFoundException}.
     * @return Check. {@link ExceptionRepresentationModel}
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionRepresentationModel handle(final NotFoundException exception) {
        return createAndLogFrom(exception);
    }

    /**
     * Handles {@link BusinessValidationException}.
     *
     * @param exception Check {@link BusinessValidationException}
     * @return Check {@link ExceptionRepresentationModel}.
     */
    @ExceptionHandler(BusinessValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionRepresentationModel handle(final BusinessValidationException exception) {
        return createAndLogFrom(exception);
    }

    /**
     * Handles {@link ConstraintViolationException}.
     *
     * @param exception Check {@link ConstraintViolationException}.
     * @return Check {@link ExceptionRepresentationModel}.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionRepresentationModel handle(final ConstraintViolationException exception) {
        return createAndLogFrom(exception);
    }

    /**
     * Creates {@link ExceptionRepresentationModel}.
     *
     * @param exception Exception.
     * @return Check {@link ExceptionRepresentationModel}.
     */
    private ExceptionRepresentationModel createAndLogFrom(final RuntimeException exception) {
        final String message = exception.getMessage();
        if (log.isDebugEnabled()) {
            log.debug(message, exception);
        } else {
            log.info(message);
        }
        return ExceptionRepresentationModel.builder()
                .message(message)
                .build();
    }

}

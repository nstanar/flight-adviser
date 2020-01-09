package com.htec.flight_management.service.util;

import org.gavaghan.geodesy.GlobalPosition;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author Nikola Stanar
 * <p>
 * Distance calculator.
 */
@FunctionalInterface
@Validated
public interface DistanceCalculator {

    /**
     * Calculates distance between point A and point B.
     *
     * @param a Position a.
     * @param b Position b.
     * @return Distance in km.
     */
    double calculateBetween(@NotNull final GlobalPosition a, @NotNull final GlobalPosition b);

}

package com.htec.flight_management.service.util.impl;

import com.htec.flight_management.service.util.DistanceCalculator;
import lombok.AllArgsConstructor;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalPosition;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @author Nikola Stanar
 * <p>
 * Distance calculator.
 * @see DistanceCalculator
 */
@Component
@AllArgsConstructor
public class DistanceCalculatorImpl implements DistanceCalculator {

    /**
     * Geodetic calculator.
     */
    private final GeodeticCalculator geodeticCalculator;

    /**
     * Calculates distance between point A and point B.
     *
     * @param a Position a.
     * @param b Position b.
     * @return Distance in km.
     * @see DistanceCalculator#calculateBetween(GlobalPosition, GlobalPosition)
     */
    public double calculateBetween(@NotNull final GlobalPosition a, @NotNull final GlobalPosition b) {
        final Ellipsoid reference = Ellipsoid.WGS84;

        return geodeticCalculator
                .calculateGeodeticCurve(reference, a, b)
                .getEllipsoidalDistance() / 1000; // Distance between Point A and Point B
    }

}

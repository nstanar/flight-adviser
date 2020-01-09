package com.htec.flight_management.common.configuration;

import org.gavaghan.geodesy.GeodeticCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nikola Stanar
 * <p>
 * Utility configuration.
 */
@Configuration
public class UtilityConfiguration {

    /**
     * Instantiates geodetic calculator.
     *
     * @return Geodetic calculator.
     */
    @Bean
    public GeodeticCalculator geodeticCalculator() {
        return new GeodeticCalculator();
    }

}

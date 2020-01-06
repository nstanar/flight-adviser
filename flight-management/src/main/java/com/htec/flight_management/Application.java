package com.htec.flight_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

/**
 * @author Nikola Stanar
 * <p>
 * Main class.
 */
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,
        UserDetailsServiceAutoConfiguration.class
}, scanBasePackages = {
        "com.htec.flight_management.common",
        "com.htec.flight_management.controller",
        "com.htec.flight_management.service",
        "com.htec.domain_starter.common",
        "com.htec.domain_starter.controller",
        "com.htec.domain_starter.service"}
)
//TODO: if time left use HAL+FORMS to make self documented api
public class Application {

    /**
     * Application entry-point.
     *
     * @param args Application args.
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

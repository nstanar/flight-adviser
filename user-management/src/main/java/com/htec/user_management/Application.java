package com.htec.user_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Nikola Stanar
 * <p>
 * Main class.
 */
@SpringBootApplication(scanBasePackages = {
        "com.htec.user_management.auth",
        "com.htec.user_management.common",
        "com.htec.user_management.oauth2",
        "com.htec.user_management.user.controller",
        "com.htec.user_management.user.service",
        "com.htec.domain_starter.common",
        "com.htec.domain_starter.controller",
        "com.htec.domain_starter.service",
})
//TODO: fix equals and hashcode everywhere
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

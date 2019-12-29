package com.htec.user_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {"com.htec.user_management", "com.htec.domain_starter.*"})
@EntityScan(basePackages = {"com.htec.user_management", "com.htec.domain_starter.*"})
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

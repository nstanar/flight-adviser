package com.htec.flight_management.common.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.annotation.EnableNeo4jAuditing;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Nikola Stanar
 */
@Configuration
@EnableTransactionManagement
@EnableNeo4jAuditing
@EnableNeo4jRepositories(basePackages = {"com.htec.flight_management.repository"})
@EntityScan(basePackages = {"com.htec.flight_management.repository.entity"})
public class Neo4jConfiguration {

}

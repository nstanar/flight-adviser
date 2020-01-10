package com.htec.flight_management.common.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nikola Stanar
 * <p>
 * Neo4j properties.
 */
@Getter
@Setter
@NoArgsConstructor
@Configuration
@ConfigurationProperties("api.neo4j")
public class Neo4jProperties {

    /**
     * Node template.
     */
    private String nodeTemplate;

    /**
     * Path template.
     */
    private String pathTemplate;

}

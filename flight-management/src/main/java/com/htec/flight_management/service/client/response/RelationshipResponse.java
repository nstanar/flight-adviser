package com.htec.flight_management.service.client.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

/**
 * @author Nikola Stanar
 * <p>
 * Relationship response.
 */
@Getter
@Setter
@NoArgsConstructor
public class RelationshipResponse {

    /**
     * Start node link.
     */
    private String start;

    /**
     * End node link.
     */
    private String end;

    /**
     * Data.
     */
    private Map<String, Object> data;

}

package com.htec.flight_management.service.client.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Nikola Stanar
 * <p>
 * Represent cheapest flight response.
 */
@Getter
@Setter
@NoArgsConstructor
public class CheapestFlightResponseBody {

    /**
     * Relationships links.
     */
    private List<String> relationships;

}

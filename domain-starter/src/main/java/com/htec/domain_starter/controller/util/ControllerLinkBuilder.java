package com.htec.domain_starter.controller.util;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Nikola Stanar
 * <p>
 * Builds controller links.
 */
public interface ControllerLinkBuilder {

    /**
     * Builds pageable link from parameters.
     *
     * @param rel               Name of the relation.
     * @param webMvcLinkBuilder Check {@link WebMvcLinkBuilder}.
     * @param pageable          Check {@link Pageable}.
     * @return Check {@link Link}.
     */
    static Link buildFrom(final String rel,
                          final WebMvcLinkBuilder webMvcLinkBuilder,
                          final Pageable pageable) {

        final UriComponentsBuilder uriBuilder = webMvcLinkBuilder.toUriComponentsBuilder();

        final int pageNumber = pageable.getPageNumber();
        final int pageSize = pageable.getPageSize();

        uriBuilder.queryParam("page", pageNumber);
        uriBuilder.queryParam("size", pageSize);
        uriBuilder.queryParam("sort", "id,asc");

        return new Link(uriBuilder.build().toString(), rel);
    }

}

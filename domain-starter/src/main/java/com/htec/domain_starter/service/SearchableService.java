package com.htec.domain_starter.service;

import com.htec.domain_starter.repository.SearchableRepository;
import com.htec.domain_starter.repository.entity.BaseEntity;
import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.domain_starter.service.dto.converter.Convertible;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Nikola Stanar
 * <p>
 * Searchable service exposing operation over DTO.
 */
public interface SearchableService<DTO extends BaseDto, ENTITY extends BaseEntity> extends Convertible<DTO, ENTITY>, CrudService<DTO, ENTITY> {

    /**
     * Finds page of DTOs matching name filter.
     *
     * @param nameFilter Name filter.
     * @param pageable   Check pageable.
     * @return Page of DTOs matching name prefix.
     */
    @PreAuthorize("isAuthenticated()")
    @Transactional(readOnly = true)
    default Page<DTO> findBy(@NotBlank @Size(min = 2) final String nameFilter, @NotNull final Pageable pageable) {
        final String enhancedNameFilter = "%" + nameFilter + "%";
        return getRepository()
                .findByNameLikeIgnoreCase(enhancedNameFilter, pageable)
                .map(getDtoConverter()::from);
    }

    /**
     * Gets searchable repository.
     *
     * @return Check {@link SearchableRepository}.
     */
    @Override
    SearchableRepository<ENTITY> getRepository();

}

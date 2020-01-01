package com.htec.domain_starter.service;

import com.htec.domain_starter.repository.SearchableRepository;
import com.htec.domain_starter.repository.entity.BaseEntity;
import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.domain_starter.service.dto.converter.Convertible;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Nikola Stanar
 * <p>
 * Searchable service exposing operation over DTO.
 */
public interface SearchableService<DTO extends BaseDto, ENTITY extends BaseEntity> extends Convertible<DTO, ENTITY>, CrudService<DTO, ENTITY> {

    /**
     * Finds page of DTOs matching name prefix.
     *
     * @param namePrefix Name prefix.
     * @param pageable   Check pageable.
     * @return Page of DTOs matching name prefix.
     */
    //TODO: check to see why validation is not fired here.
    default Page<DTO> findBy(@Size(min = 2) final String namePrefix, @NotNull final Pageable pageable) {
        return getRepository()
                .findByNameStartingWithIgnoreCase(namePrefix, pageable)
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

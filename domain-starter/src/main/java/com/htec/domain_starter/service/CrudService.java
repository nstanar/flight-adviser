package com.htec.domain_starter.service;

import com.htec.domain_starter.repository.entity.BaseEntity;
import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.domain_starter.service.dto.converter.Convertible;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Nikola Stanar
 * <p>
 * Service for CRUD operations over DTO.
 */
//TODO: AOP
//TODO: add bulk insert
public interface CrudService<DTO extends BaseDto, ENTITY extends BaseEntity> extends Convertible<DTO, ENTITY> {

    /**
     * Finds page of DTOs.
     *
     * @param pageable Check {@link Pageable}.
     * @return Page of DTOs.
     */
    default Page<DTO> findAll(final Pageable pageable) {
        return getRepository()
                .findAll(pageable)
                .map(getDtoConverter()::from);
    }

    /**
     * Finds optional DTO by id.
     *
     * @param id ID of the DTO.
     * @return Optional DTO.
     */
    default Optional<DTO> findBy(@NotNull final Long id) {
        return getRepository()
                .findById(id)
                .map(getDtoConverter()::from);
    }

    /**
     * Creates DTO from method argument.
     *
     * @param dto Dto holding content that is about to be created.
     * @return Id of the created dto.
     */
    default Long create(@NotNull @Valid final DTO dto) {
        final ENTITY entity = getDtoConverter().from(dto);
        return getRepository()
                .save(entity)
                .getId();
    }

    /**
     * Creates DTOs from method argument.
     *
     * @param dtoS Stream of dtoS holding content that is about to be created.
     */
    //TODO: extract this.
    default void create(final @NotEmpty Collection<@NotNull @Valid DTO> dtoS) {
        final Set<ENTITY> entities = dtoS
                .stream()
                .map(getDtoConverter()::from)
                .collect(Collectors.toSet());
        getRepository().saveAll(entities);
    }

    /**
     * Gets jpa repository.
     *
     * @return Check {@link JpaRepository}.
     */
    JpaRepository<ENTITY, Long> getRepository();

}

package com.htec.domain_starter.service;

import com.htec.domain_starter.repository.entity.BaseEntity;
import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.domain_starter.service.dto.converter.Convertible;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    default Page<DTO> find(final Pageable pageable) {
        return getUserRepository()
                .findAll(pageable)
                .map(getUserDtoConverter()::from);
    }

    /**
     * Finds optional DTO by id.
     *
     * @param id ID of the DTO.
     * @return Optional DTO.
     */
    @Transactional(readOnly = true)
    default Optional<DTO> findBy(@NotNull final Long id) {
        return getUserRepository()
                .findById(id)
                .map(getUserDtoConverter()::from);
    }

    /**
     * Creates DTO from method argument.
     *
     * @param dto Dto holding content that is about to be created.
     * @return Id of the created dto.
     */
    @Transactional
    default DTO createFrom(@NotNull @Valid final DTO dto) {
        final ENTITY entity = getUserDtoConverter().from(dto);
        final ENTITY createdEntity = getUserRepository()
                .save(entity);
        return getUserDtoConverter().from(createdEntity);
    }

    /**
     * Update DTO from method argument.
     *
     * @param id  Id of the dto.
     * @param dto DTO holding update content.
     * @return Optional updated DTO if id exist, else empty.
     */
    @Transactional
    default Optional<DTO> updateFrom(@NotNull final Long id, @NotNull @Valid final DTO dto) {
        final Optional<ENTITY> optionalEntity = getUserRepository().findById(id);
        return optionalEntity
                .map(entity -> getUserDtoConverter().from(dto, entity))
                .map(getUserDtoConverter()::from);
    }

    /**
     * Deletes DTO by id.
     *
     * @param id Id of the DTO.
     * @return true if found; otherwise false.
     */
    @Transactional
    default boolean deleteBy(final Long id) {
        final boolean exists = getUserRepository().existsById(id);
        if (exists) {
            getUserRepository().deleteById(id);
        }
        return exists;
    }

    /**
     * Creates DTOs from method argument.
     *
     * @param dtoS Stream of dtoS holding content that is about to be created.
     */
    //TODO: extract this.
    @Transactional
    default void createFrom(final @NotEmpty Collection<@NotNull @Valid DTO> dtoS) {
        final Set<ENTITY> entities = dtoS
                .stream()
                .map(getUserDtoConverter()::from)
                .collect(Collectors.toSet());
        getUserRepository().saveAll(entities);
    }

    /**
     * Gets jpa repository.
     *
     * @return Check {@link JpaRepository}.
     */
    JpaRepository<ENTITY, Long> getUserRepository();

}

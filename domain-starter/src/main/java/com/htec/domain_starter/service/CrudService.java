package com.htec.domain_starter.service;

import com.htec.domain_starter.repository.entity.BaseEntity;
import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.domain_starter.service.dto.converter.Convertible;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

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
@Transactional
@Validated
public interface CrudService<DTO extends BaseDto, ENTITY extends BaseEntity> extends Convertible<DTO, ENTITY> {

    /**
     * Finds page of DTOs.
     *
     * @param pageable Check {@link Pageable}.
     * @return Page of DTOs.
     */
    @PreAuthorize("isAuthenticated()")
    @Transactional(readOnly = true)
    default Page<DTO> find(final Pageable pageable) {
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
    @PreAuthorize("isAuthenticated()")
    @Transactional(readOnly = true)
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
    @PreAuthorize("isAuthenticated()")
    default DTO createFrom(@NotNull @Valid final DTO dto) {
        final ENTITY entity = getDtoConverter().from(dto);
        final ENTITY createdEntity = getRepository()
                .save(entity);
        return getDtoConverter().from(createdEntity);
    }

    /**
     * Update DTO from method argument.
     *
     * @param id  Id of the dto.
     * @param dto DTO holding update content.
     * @return Optional updated DTO if id exist, else empty.
     */
    //TODO: fix this for comments
    @PreAuthorize("hasRole('ADMIN')")
    default Optional<DTO> updateFrom(@NotNull final Long id, @NotNull @Valid final DTO dto) {
        final Optional<ENTITY> optionalEntity = getRepository().findById(id);
        return optionalEntity
                .map(entity -> getRepository().save(getDtoConverter().from(dto, entity)))
                .map(getDtoConverter()::from);
    }

    /**
     * Deletes DTO by id.
     *
     * @param id Id of the DTO.
     * @return Optionally deleted DTO if existed.
     */
    @PreAuthorize("hasRole('ADMIN')")
    //TODO: fix this for comments
    default Optional<DTO> deleteBy(final Long id) {
        final Optional<ENTITY> optionalEntity = getRepository().findById(id);
        Optional<DTO> deletedDto = Optional.empty();
        if (optionalEntity.isPresent()) {
            deletedDto = Optional.of(getDtoConverter().from(optionalEntity.get()));
            getRepository().deleteById(id);
        }
        return deletedDto;
    }

    /**
     * Creates DTOs from method argument.
     *
     * @param dtoS Stream of dtoS holding content that is about to be created.
     */
    @PreAuthorize("hasRole('ADMIN')")
    default void createFrom(final @NotEmpty Collection<@NotNull @Valid DTO> dtoS) {
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

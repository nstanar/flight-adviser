package com.htec.domain_starter.service;

import com.htec.domain_starter.repository.entity.BaseEntity;
import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.domain_starter.service.dto.converter.Convertible;
import com.htec.domain_starter.service.validation.chain.BusinessValidatorChain;
import com.htec.domain_starter.service.validation.exception.NotFoundException;
import com.htec.domain_starter.service.validation.marker.Create;
import com.htec.domain_starter.service.validation.marker.Update;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostAuthorize;
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

import static com.htec.domain_starter.common.constants.MessageSourceKeys.RESOURCE_DOES_NOT_EXIST;
import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

/**
 * @author Nikola Stanar
 * <p>
 * Service for CRUD operations over DTO.
 */
//TODO: AOP
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
    default Optional<DTO> findById(@NotNull final Long id) {
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
    @PreAuthorize("hasRole('ADMIN')")
    default DTO createFrom(@NotNull @Valid final DTO dto) {
        getBusinessValidatorChain().ifPresent(businessValidatorChain -> businessValidatorChain.validateFor(Create.class, dto));

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
    @PostAuthorize("hasRole('ADMIN')")
    default DTO updateFrom(@NotNull final Long id, @NotNull @Valid final DTO dto) {
        getBusinessValidatorChain().ifPresent(businessValidatorChain -> businessValidatorChain.validateFor(Update.class, dto));

        return getRepository()
                .findById(id)
                .map(entity -> getRepository().save(getDtoConverter().from(dto, entity)))
                .map(getDtoConverter()::from)
                .orElseThrow(() -> {
                    final String message = getMessageSource().getMessage(RESOURCE_DOES_NOT_EXIST, new Object[]{id}, getLocale());
                    throw new NotFoundException(message);
                });
    }

    /**
     * Deletes DTO by id.
     *
     * @param id Id of the DTO.
     * @return Optionally deleted DTO if existed.
     */
    @PostAuthorize("hasRole('ADMIN')")
    default DTO deleteById(final Long id) {
        return getRepository()
                .findById(id)
                .map(entity -> {
                    final DTO deletedDto = getDtoConverter().from(entity);
                    getRepository().deleteById(id);
                    return deletedDto;
                }).orElseThrow(() -> {
                    final String message = getMessageSource().getMessage(RESOURCE_DOES_NOT_EXIST, new Object[]{id}, getLocale());
                    throw new NotFoundException(message);
                });
    }

    /**
     * Creates DTOs from method argument.
     *
     * @param dtoS DTOs holding content that is about to be created.
     */
    @PreAuthorize("hasRole('ADMIN')")
    default void createFrom(final @NotEmpty Collection<@NotNull @Valid DTO> dtoS) {
        final Set<ENTITY> entities = dtoS
                .parallelStream()
                .map(getDtoConverter()::from)
                .collect(Collectors.toSet());
        getRepository().saveAll(entities);
    }

    /**
     * Deletes DTOs from method argument.
     *
     * @param dtoS DTOs holding content that is about to be created.
     */
    @PreAuthorize("hasRole('ADMIN')")
    default void delete(final @NotEmpty Collection<@NotNull @Valid DTO> dtoS) {
        final Set<ENTITY> entities = dtoS
                .parallelStream()
                .map(getDtoConverter()::from)
                .collect(Collectors.toSet());
        getRepository().deleteAll(entities);
    }

    /**
     * Gets business validator chain.
     *
     * @return Optional validator chain.
     */
    Optional<BusinessValidatorChain<DTO>> getBusinessValidatorChain();

    /**
     * Gets jpa repository.
     *
     * @return Check {@link JpaRepository}.
     */
    JpaRepository<ENTITY, Long> getRepository();

    /**
     * Gets message source.
     *
     * @return Message source.
     */
    MessageSource getMessageSource();

}

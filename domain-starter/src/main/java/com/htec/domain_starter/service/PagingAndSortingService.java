package com.htec.domain_starter.service;

import com.htec.domain_starter.repository.BaseEntity;
import com.htec.domain_starter.service.dto.BaseDto;
import com.htec.domain_starter.service.dto.converter.Convertible;
import com.htec.domain_starter.service.validation.chain.BusinessValidatorChain;
import com.htec.domain_starter.service.validation.marker.Create;
import com.htec.domain_starter.service.validation.marker.Update;
import com.htec.domain_starter.service.validation.util.ExceptionUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
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

/**
 * @author Nikola Stanar
 * <p>
 * Paging and sorting service exposing operations over DTO.
 */
//TODO: AOP logging if time left
@Transactional
@Validated
public interface PagingAndSortingService<D extends BaseDto, E extends BaseEntity> extends Convertible<D, E> {

    /**
     * Finds page of DTOs.
     *
     * @param pageable Check {@link Pageable}.
     * @return Page of DTOs.
     */
    @PreAuthorize("isAuthenticated()")
    @Transactional(readOnly = true)
    default Page<D> find(final Pageable pageable) {
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
    default Optional<D> findById(@NotNull final Long id) {
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
    default D createFrom(@NotNull @Valid final D dto) {
        getBusinessValidatorChain().ifPresent(businessValidatorChain -> businessValidatorChain.validateFor(Create.class, dto));

        final E entity = getDtoConverter().from(dto);
        final E createdEntity = getRepository()
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
    default D updateFrom(@NotNull final Long id, @NotNull @Valid final D dto) {
        dto.setId(id);
        getBusinessValidatorChain().ifPresent(businessValidatorChain -> businessValidatorChain.validateFor(Update.class, dto));

        return getRepository()
                .findById(id)
                .map(entity -> getRepository().save(getDtoConverter().from(dto, entity)))
                .map(getDtoConverter()::from)
                .orElseThrow(() -> getExceptionUtil().createNotFoundExceptionFrom(RESOURCE_DOES_NOT_EXIST, new Object[]{id}));
    }

    /**
     * Deletes DTO by id.
     *
     * @param id Id of the DTO.
     * @return Optionally deleted DTO if existed.
     */
    @PostAuthorize("hasRole('ADMIN')")
    default D deleteById(final Long id) {
        return getRepository()
                .findById(id)
                .map(entity -> {
                    final D deletedDto = getDtoConverter().from(entity);
                    getRepository().deleteById(id);
                    return deletedDto;
                })
                .orElseThrow(() -> getExceptionUtil().createNotFoundExceptionFrom(RESOURCE_DOES_NOT_EXIST, new Object[]{id}));
    }

    /**
     * Creates DTOs from method argument.
     *
     * @param dtoS DTOs holding content that is about to be created.
     */
    @PreAuthorize("hasRole('ADMIN')")
    default void createFrom(final @NotEmpty Collection<@NotNull @Valid D> dtoS) {
        final Set<E> entities = dtoS
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
    default void delete(final @NotEmpty Collection<@NotNull @Valid D> dtoS) {
        final Set<E> entities = dtoS
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
    Optional<BusinessValidatorChain<D>> getBusinessValidatorChain();

    /**
     * Gets repository.
     *
     * @return Check {@link PagingAndSortingRepository}.
     */
    PagingAndSortingRepository<E, Long> getRepository();

    /**
     * Gets exception util.
     *
     * @return Exception util.
     */
    ExceptionUtil getExceptionUtil();

}

package com.htec.flight_management.service.impl;

import com.htec.domain_starter.service.dto.converter.DtoConverter;
import com.htec.domain_starter.service.validation.chain.BusinessValidatorChain;
import com.htec.domain_starter.service.validation.util.ExceptionUtil;
import com.htec.flight_management.repository.CommentRepository;
import com.htec.flight_management.repository.entity.Comment;
import com.htec.flight_management.service.CommentService;
import com.htec.flight_management.service.dto.CommentDto;
import com.htec.flight_management.service.dto.converter.CommentDtoConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * @author Nikola Stanar
 * <p>
 * Service exposing operations over comment.
 * @see CommentService
 */
@Service
@Slf4j
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    /**
     * Repository for comment.
     */
    private final CommentRepository repository;

    /**
     * Dto converter for comment.
     */
    private final CommentDtoConverter dtoConverter;

    /**
     * Exception util.
     */
    private final ExceptionUtil exceptionUtil;

    /**
     * Finds page of comments belonging to city.
     *
     * @param cityId   City id.
     * @param pageable Check pageable.
     * @return Page of comments belonging to city;
     * @see CommentService#findAllByCityId(Long, Pageable)
     */
    @Override
    public Page<CommentDto> findAllByCityId(final @NotNull Long cityId, final @NotNull Pageable pageable) {
        log.info("Fetching {} of comments belonging to city {}. ", pageable, cityId);
        return repository
                .findAllByCityId(cityId, pageable)
                .map(dtoConverter::from);
    }

    /**
     * Gets business validator chain.
     *
     * @return Business validator chain.
     */
    @Override
    public Optional<BusinessValidatorChain<CommentDto, Long>> getBusinessValidatorChain() {
        return Optional.empty();
    }

    /**
     * Gets repository.
     *
     * @return Repository.
     * @see CommentService#getRepository()
     */
    @Override
    public PagingAndSortingRepository<Comment, Long> getRepository() {
        return repository;
    }

    /**
     * Gets exception util.
     *
     * @return Exception util.
     * @see CommentService#getExceptionUtil()
     */
    @Override
    public ExceptionUtil getExceptionUtil() {
        return exceptionUtil;
    }

    /**
     * Gets dto converter.
     *
     * @return Dto converter.
     * @see CommentService#getDtoConverter()
     */
    @Override
    public DtoConverter<CommentDto, Comment, Long> getDtoConverter() {
        return dtoConverter;
    }
}

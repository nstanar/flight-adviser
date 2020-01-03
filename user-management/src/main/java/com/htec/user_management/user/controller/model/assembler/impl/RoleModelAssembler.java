package com.htec.user_management.user.controller.model.assembler.impl;

import com.htec.user_management.user.controller.model.RoleModel;
import com.htec.user_management.user.service.dto.RoleDto;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

/**
 * @author Nikola Stanar
 * <p>
 * Model assembler for role.
 */
@Component
@NoArgsConstructor
public class RoleModelAssembler implements RepresentationModelAssembler<RoleDto, RoleModel> {

    /**
     * Assembles role model from dto.
     *
     * @param dto Dto used in assembly process.
     * @return Role model.
     */
    @Override
    public RoleModel toModel(final RoleDto dto) {
        return RoleModel.builder()
                .name(dto.getName())
                .build();
    }
}

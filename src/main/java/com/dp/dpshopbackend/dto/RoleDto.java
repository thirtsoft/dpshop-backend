package com.dp.dpshopbackend.dto;

import com.dp.dpshopbackend.enumeration.RoleName;
import com.dp.dpshopbackend.models.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDto {

    private RoleName name;

    public RoleDto formEntityToDto(Role role) {
        if (role == null) {
            return null;
        }

        return RoleDto.builder()
                .name(role.getName())
                .build();
    }

    public Role fromDtoToEntity(RoleDto roleDto) {
        if (roleDto == null) {
            return null;
        }

        Role role = new Role();
        role.setName(roleDto.getName());

        return role;
    }
}

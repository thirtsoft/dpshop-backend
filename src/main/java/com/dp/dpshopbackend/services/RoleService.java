package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.RoleDto;
import com.dp.dpshopbackend.enumeration.RoleName;

import java.util.List;

public interface RoleService {

    RoleDto saveRole(RoleDto roleDto);

    RoleDto updateRole(Long idRole, RoleDto roleDto);

    RoleDto findRoleById(Long idRole);

    RoleDto findByName(RoleName roleName);

    List<RoleDto> findAllRoles();

    void deleteRole(Long idRole);
}

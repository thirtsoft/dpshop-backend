package com.dp.dpshopbackend.services.impl;

import com.dp.dpshopbackend.dto.RoleDto;
import com.dp.dpshopbackend.enumeration.RoleName;
import com.dp.dpshopbackend.exceptions.ResourceNotFoundException;
import com.dp.dpshopbackend.models.Role;
import com.dp.dpshopbackend.repository.RoleRepository;
import com.dp.dpshopbackend.services.AuthorityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleDto saveRole(RoleDto roleDto) {
        return RoleDto.formEntityToDto(
                roleRepository.save(
                        RoleDto.fromDtoToEntity(roleDto))
        );
    }

    @Override
    public RoleDto updateRole(Long idRole, RoleDto roleDto) {
        if (!roleRepository.existsById(idRole)) {
            throw new ResourceNotFoundException("Role not found");
        }

        Optional<Role> roleOptional = roleRepository.findById(idRole);

        if (!roleOptional.isPresent()) {
            throw new ResourceNotFoundException("RoleDto not found");
        }

        RoleDto roleDtoResult = RoleDto.formEntityToDto(roleOptional.get());
        roleDtoResult.setName(roleDto.getName());

        return RoleDto.formEntityToDto(
                roleRepository.save(
                        RoleDto.fromDtoToEntity(roleDtoResult)
                )
        );
    }

    @Override
    public RoleDto findRoleById(Long idRole) {
        if (idRole == null) {
            log.error("Role Id is null");
            return null;
        }

        Optional<Role> roleOptional = roleRepository.findById(idRole);

        return Optional.of(RoleDto.formEntityToDto(roleOptional.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Role avec l'Id = " + idRole + "n'a été trouvé")
        );
    }

    @Override
    public RoleDto findByName(RoleName roleName) {
        if (roleName == null) {
            log.error("Role Id is null");
            return null;
        }

        Optional<Role> roleOptional = roleRepository.findByName(roleName);

        return Optional.of(RoleDto.formEntityToDto(roleOptional.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Role avec l'Id = " + roleOptional + "n'a été trouvé")
        );
    }

    @Override
    public List<RoleDto> findAllRoles() {
        return roleRepository.findAll().stream()
                .map(RoleDto::formEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRole(Long idRole) {
        if (idRole == null) {
            log.error("Role Id is null");
            return;
        }
        roleRepository.deleteById(idRole);
    }
}

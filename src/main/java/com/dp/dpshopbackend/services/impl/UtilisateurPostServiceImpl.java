package com.dp.dpshopbackend.services.impl;

import com.dp.dpshopbackend.dto.RoleDto;
import com.dp.dpshopbackend.dto.UtilisateurPOSTDto;
import com.dp.dpshopbackend.enumeration.RoleName;
import com.dp.dpshopbackend.exceptions.ResourceNotFoundException;
import com.dp.dpshopbackend.message.request.LoginForm;
import com.dp.dpshopbackend.message.request.SignUpForm;
import com.dp.dpshopbackend.message.response.JwtsResponse;
import com.dp.dpshopbackend.models.Role;
import com.dp.dpshopbackend.repository.UtilisateurRepository;
import com.dp.dpshopbackend.security.jwt.JwtsProvider;
import com.dp.dpshopbackend.security.service.UserPrinciple;
import com.dp.dpshopbackend.services.AuthorityService;
import com.dp.dpshopbackend.services.UtilisateurPostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class UtilisateurPostServiceImpl implements UtilisateurPostService {

    private final UtilisateurRepository utilisateurRepository;

    private final AuthenticationManager authenticationManager;

    private final AuthorityService authorityService;

    private final PasswordEncoder passwordEncoder;

    private final JwtsProvider jwtsProvider;

    @Autowired
    public UtilisateurPostServiceImpl(UtilisateurRepository utilisateurRepository,
                                      AuthenticationManager authenticationManager,
                                      AuthorityService authorityService,
                                      PasswordEncoder passwordEncoder,
                                      JwtsProvider jwtsProvider) {
        this.utilisateurRepository = utilisateurRepository;
        this.authenticationManager = authenticationManager;
        this.authorityService = authorityService;
        this.passwordEncoder = passwordEncoder;
        this.jwtsProvider = jwtsProvider;
    }

    @Override
    public UtilisateurPOSTDto registerUser(SignUpForm signUpRequest) {
        if (utilisateurRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new ResourceNotFoundException("Fail -> Username is already in use!");
        }

        if (utilisateurRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new ResourceNotFoundException("Fail -> Email is already in use!");
        }

        UtilisateurPOSTDto utilisateurPOSTDto = new UtilisateurPOSTDto(
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword())
        );
        String[] roleArr = signUpRequest.getRoles();
        //    Set roleArr = signUpRequest.getRole();
        Set roles = new HashSet<>();

     /*   roleArr.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = (RoleDto.fromDtoToEntity(authorityService.findByName(RoleName.ROLE_ADMIN)));
                    roles.add(adminRole);

                    break;
                case "manager":
                    Role managerRole = (RoleDto.fromDtoToEntity(authorityService.findByName(RoleName.ROLE_MANAGER)));
                    roles.add(managerRole);

                    break;
                default:
                    Role userRole = (RoleDto.fromDtoToEntity(authorityService.findByName(RoleName.ROLE_USER)));
                    roles.add(userRole);
            }
        });*/

        if (roleArr == null) {
            Role userRole = (RoleDto.fromDtoToEntity(authorityService.findByName(RoleName.ROLE_USER)));

            roles.add(userRole);
        }

        for (Object role : roleArr) {
            switch (role.toString()) {
                case "admin":
                    Role adminRole = (RoleDto.fromDtoToEntity(authorityService.findByName(RoleName.ROLE_ADMIN)));

                    roles.add(adminRole);
                    break;
                case "manager":
                    Role managerRole = (RoleDto.fromDtoToEntity(authorityService.findByName(RoleName.ROLE_MANAGER)));
                    roles.add(managerRole);
                    break;
                case "user":
                    Role userRole = (RoleDto.fromDtoToEntity(authorityService.findByName(RoleName.ROLE_USER)));
                    roles.add(userRole);

                    break;
                default:
                    new ResourceNotFoundException("Aucun role specified");
            }
        }

        utilisateurPOSTDto.setRoleDtos((Set<RoleDto>) RoleDto.formEntityToDto((Role) roles));

        return UtilisateurPOSTDto.fromEntityToDto(
                utilisateurRepository.save(
                        UtilisateurPOSTDto.fromDtoToEntity(utilisateurPOSTDto)
                )
        );

    }

    @Override
    public UtilisateurPOSTDto authenticateUser(LoginForm loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtsProvider.generatedJwtToken(authentication);

        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();

        List<String> roles = userPrinciple.getAuthorities()
                .stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new JwtsResponse(jwt,
                userPrinciple.getId(),
                userPrinciple.getUsername(),
                userPrinciple.getPassword(),
                roles);


    }
}

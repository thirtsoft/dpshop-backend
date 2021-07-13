package com.dp.dpshopbackend.services.impl;

import com.dp.dpshopbackend.dto.RoleDto;
import com.dp.dpshopbackend.dto.UtilisateurPOSTDto;
import com.dp.dpshopbackend.enumeration.RoleName;
import com.dp.dpshopbackend.exceptions.ResourceNotFoundException;
import com.dp.dpshopbackend.message.request.LoginForm;
import com.dp.dpshopbackend.message.request.SignUpForm;
import com.dp.dpshopbackend.message.response.JwtsResponse;
import com.dp.dpshopbackend.models.Role;
import com.dp.dpshopbackend.models.Utilisateur;
import com.dp.dpshopbackend.repository.UtilisateurRepository;
import com.dp.dpshopbackend.security.jwt.JwtsProvider;
import com.dp.dpshopbackend.security.service.UserPrinciple;
import com.dp.dpshopbackend.services.AuthorityService;
import com.dp.dpshopbackend.services.UtilisateurPostDtoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class UtilisateurPostDtoServiceImpl implements UtilisateurPostDtoService {

    private final UtilisateurRepository utilisateurRepository;

    private final AuthenticationManager authenticationManager;

    private final AuthorityService authorityService;

    private final PasswordEncoder passwordEncoder;

    private final JwtsProvider jwtsProvider;

    @Autowired
    public UtilisateurPostDtoServiceImpl(UtilisateurRepository utilisateurRepository,
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
    public UtilisateurPOSTDto save(UtilisateurPOSTDto utilisateurPOSTDto) {
        return UtilisateurPOSTDto.fromEntityToDto(
                utilisateurRepository.save(
                        UtilisateurPOSTDto.fromDtoToEntity(utilisateurPOSTDto))
        );
    }

    @Override
    public UtilisateurPOSTDto update(Long id, UtilisateurPOSTDto utilisateurPOSTDto) {
        if (!utilisateurRepository.existsById(id)) {
            throw new ResourceNotFoundException("Utilisateur not found");
        }

        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(id);

        if (!utilisateurOptional.isPresent()) {
            throw new ResourceNotFoundException("Utilisateur not found");
        }

        UtilisateurPOSTDto utilisateurPOSTDtoResult = UtilisateurPOSTDto.fromEntityToDto(utilisateurOptional.get());
        utilisateurPOSTDtoResult.setUsername(utilisateurPOSTDto.getUsername());
        utilisateurPOSTDtoResult.setEmail(utilisateurPOSTDto.getEmail());
        utilisateurPOSTDtoResult.setPassword(utilisateurPOSTDto.getPassword());
        utilisateurPOSTDtoResult.setRoleDtos(utilisateurPOSTDto.getRoleDtos());

        return UtilisateurPOSTDto.fromEntityToDto(
                utilisateurRepository.save(
                        UtilisateurPOSTDto.fromDtoToEntity(utilisateurPOSTDtoResult)
                )
        );
    }

    @Override
    public boolean updateUsernameOfUtilisateur(String username, String newUsername) {
        Optional<Utilisateur> existsUser = this.utilisateurRepository.findByUsername(username);
        Utilisateur user;
        if (existsUser.isPresent()) {
            user = existsUser.get();
            user.setUsername(newUsername);
            this.utilisateurRepository.save(user);
            return true;
        }

        return false;
    }

    @Override
    public boolean updatePasswordofUtilisateur(String username, String oldPass, String newPass) {
        Optional<Utilisateur> existsUser = this.utilisateurRepository.findByUsername(username);
        Utilisateur user;
        if (existsUser.isPresent()) {
            user = existsUser.get();

            if (passwordEncoder.matches(oldPass, user.getPassword())) {
                user.setPassword(passwordEncoder.encode(newPass));
                this.utilisateurRepository.save(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public UtilisateurPOSTDto findById(Long id) {
        if (id == null) {
            log.error("Utilisateur Id is null");
            return null;
        }

        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(id);

        return Optional.of(UtilisateurPOSTDto.fromEntityToDto(utilisateurOptional.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Utilisateur avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public UtilisateurPOSTDto findUtilisateurByUsername(String username) {
        if (!StringUtils.hasLength(username)) {
            log.error("Utilisateur Username is null");
        }

        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findByUsername(username);


        return Optional.of(UtilisateurPOSTDto.fromEntityToDto(utilisateurOptional.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Utilisateur avec le username = " + username + "n'a été trouvé")
        );
    }

    @Override
    public List<UtilisateurPOSTDto> findAll() {
        return utilisateurRepository.findAll().stream()
                .map(UtilisateurPOSTDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Utilisateur Id is null");
            return;
        }
        utilisateurRepository.deleteById(id);

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
        Set<Role> roles = new HashSet<>();

        if (roleArr == null) {
            roles.add(Optional.of(RoleDto.fromDtoToEntity(authorityService.findByName(RoleName.ROLE_USER))).orElseThrow(() ->
                    new ResourceNotFoundException("Aucnun  Role user a été trouvé")
                    )
            );
        }

        for (String role: roleArr) {
            switch (role.toLowerCase()) {
                case "admin":
                    roles.add(Optional.of(RoleDto.fromDtoToEntity(authorityService.findByName(RoleName.ROLE_ADMIN))).orElseThrow(() ->
                                    new ResourceNotFoundException("Fail! -> Cause: Admin Role not find.")
                            )
                    );
                    break;
                case "manager":
                    roles.add(Optional.of(RoleDto.fromDtoToEntity(authorityService.findByName(RoleName.ROLE_MANAGER))).orElseThrow(() ->
                                    new ResourceNotFoundException("Fail! -> Cause: Manager Role not find.")
                            )
                    );
                    break;
                case "user":
                    roles.add(Optional.of(RoleDto.fromDtoToEntity(authorityService.findByName(RoleName.ROLE_USER))).orElseThrow(() ->
                                    new ResourceNotFoundException("Fail! -> Cause: User Role not find.")
                            )
                    );
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

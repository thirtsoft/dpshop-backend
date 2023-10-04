package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.AuthApi;
import com.dp.dpshopbackend.dto.HistoriqueLoginDto;
import com.dp.dpshopbackend.dto.UtilisateurDto;
import com.dp.dpshopbackend.enumeration.RoleName;
import com.dp.dpshopbackend.exceptions.ResourceNotFoundException;
import com.dp.dpshopbackend.message.request.LoginForm;
import com.dp.dpshopbackend.message.request.SignUpForm;
import com.dp.dpshopbackend.message.response.JwtResponse;
import com.dp.dpshopbackend.models.Role;
import com.dp.dpshopbackend.models.Utilisateur;
import com.dp.dpshopbackend.repository.ConfirmTokenRepository;
import com.dp.dpshopbackend.repository.RoleRepository;
import com.dp.dpshopbackend.repository.UtilisateurRepository;
import com.dp.dpshopbackend.security.jwt.JwtProvider;
import com.dp.dpshopbackend.security.service.UserPrinciple;
import com.dp.dpshopbackend.services.EmailService;
import com.dp.dpshopbackend.services.HistoriqueLoginService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@Slf4j
@AllArgsConstructor
public class AuthController implements AuthApi {

    private final AuthenticationManager authenticationManager;

    private final UtilisateurRepository utilisateurRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final JwtProvider jwtProvider;

    private final ConfirmTokenRepository confirmTokenRepository;

    private final EmailService emailService;

    private final HistoriqueLoginService historiqueLoginService;

    @Override
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
        UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(userDetails.getId());
        Utilisateur utilisateur = optionalUtilisateur.get();
        UtilisateurDto utilisateurDto = UtilisateurDto.fromEntityToDto(utilisateur);
        HistoriqueLoginDto historiqueLoginDto = new HistoriqueLoginDto();
        historiqueLoginDto.setUtilisateurDto(utilisateurDto);
        historiqueLoginDto.setAction("Connection");
        historiqueLoginDto.setStatus("Valider");
        historiqueLoginDto.setActif(true);
        historiqueLoginDto.setCreatedDate(new Date());
        historiqueLoginService.saveHistoriqueLogin(historiqueLoginDto);

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @Override
    public ResponseEntity<?> signUp(SignUpForm signUpForm) {
        if (utilisateurRepository.existsByUsername(signUpForm.getUsername())) {
            throw new ResourceNotFoundException("Fail -> Error: Username is already taken!");
        }
        if (utilisateurRepository.existsByEmail(signUpForm.getEmail())) {
            throw new ResourceNotFoundException("Error: Email is already in use!");
        }
        // Create new user's account
        Utilisateur utilisateur = new Utilisateur(
                signUpForm.getName(),
                signUpForm.getUsername(),
                signUpForm.getEmail(),
                encoder.encode(signUpForm.getPassword()
                )
        );
        String[] strRoles = signUpForm.getRoles();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = (roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
            roles.add(userRole);

        }
        for (String role : strRoles) {
            switch (role.toLowerCase()) {
                case "admin":
                    roles.add(roleRepository.findByName(RoleName.ROLE_ADMIN).get());
                    break;

                case "manager":
                    roles.add(roleRepository.findByName(RoleName.ROLE_MANAGER).get());
                    break;

                case "assistant":
                    roles.add(roleRepository.findByName(RoleName.ROLE_ASSISTANT).get());
                    break;

                case "user":
                    roles.add(roleRepository.findByName(RoleName.ROLE_USER).get());
                    break;

                default:
                    roles.add(roleRepository.findByName(RoleName.ROLE_USER).get());
                    //    return ResponseEntity.badRequest().body("Specified role not found");

            }
        }

        utilisateur.setRoles(roles);
        utilisateur.setActivated(true);
        utilisateur.setActif(true);
        return ResponseEntity.ok(utilisateurRepository.save(utilisateur));
    }

    @Override
    public ResponseEntity<?> registerUser(SignUpForm signUpForm) {
        if (utilisateurRepository.existsByUsername(signUpForm.getUsername())) {
            throw new ResourceNotFoundException("Fail -> Error: Username is already taken!");
        }
        if (utilisateurRepository.existsByEmail(signUpForm.getEmail())) {
            throw new ResourceNotFoundException("Error: Email is already in use!");
        }
        Utilisateur utilisateur = new Utilisateur(
                signUpForm.getUsername(),
                signUpForm.getEmail(),
                encoder.encode(signUpForm.getPassword()
                )
        );
        String[] strRoles = signUpForm.getRoles();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            roles.add(roleRepository.findByName(RoleName.ROLE_USER).get());
        }
        for (String role : strRoles) {
            switch (role.toLowerCase()) {
                case "admin":
                    roles.add(roleRepository.findByName(RoleName.ROLE_ADMIN).get());
                    break;

                case "manager":
                    roles.add(roleRepository.findByName(RoleName.ROLE_MANAGER).get());
                    break;

                case "user":
                    roles.add(roleRepository.findByName(RoleName.ROLE_USER).get());
                    break;

                default:
                    return ResponseEntity.badRequest().body("Specified role not found");
            }
        }

        utilisateur.setRoles(roles);
        utilisateur.setActivated(true);
        utilisateur.setActif(true);
        return ResponseEntity.ok(utilisateurRepository.save(utilisateur));
    }

    @Override
    public String getcurrentUserName(Principal principal) {
        return null;
    }

    @Override
    public String getcurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        System.out.println("CurrentUser " + currentUserName);
        return currentUserName;
    }
}
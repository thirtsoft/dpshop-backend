package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.AuthApi;
import com.dp.dpshopbackend.dto.HistoriqueLoginDto;
import com.dp.dpshopbackend.dto.UtilisateurDto;
import com.dp.dpshopbackend.enumeration.RoleName;
import com.dp.dpshopbackend.exceptions.ResourceNotFoundException;
import com.dp.dpshopbackend.message.request.LoginForm;
import com.dp.dpshopbackend.message.request.SignUpForm;
import com.dp.dpshopbackend.message.response.JwtsResponse;
import com.dp.dpshopbackend.models.Role;
import com.dp.dpshopbackend.models.Utilisateur;
import com.dp.dpshopbackend.repository.ConfirmTokenRepository;
import com.dp.dpshopbackend.repository.RoleRepository;
import com.dp.dpshopbackend.repository.UtilisateurRepository;
import com.dp.dpshopbackend.security.jwt.JwtsProvider;
import com.dp.dpshopbackend.security.service.UserPrinciple;
import com.dp.dpshopbackend.services.EmailService;
import com.dp.dpshopbackend.services.HistoriqueLoginService;
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
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"https://soulbusinesse.com"})
@RestController
@Slf4j
public class AuthController implements AuthApi {


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtsProvider jwtsProvider;

    @Autowired
    private ConfirmTokenRepository confirmTokenRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private HistoriqueLoginService historiqueLoginService;


    /*
        @Override
        public ResponseEntity<UtilisateurPOSTDto> signIn(UtilisateurPOSTDto utilisateurPOSTDto) {
            LoginForm loginForm = new LoginForm();
            loginForm.setUsername(utilisateurPOSTDto.getUsername());
            loginForm.setPassword(loginForm.getPassword());

            UtilisateurPOSTDto utilisateurPOSTDtomResult = utilisateurPostService.authenticateUser(loginForm);

            if (utilisateurPOSTDtomResult != null) {
                log.info("User connected succefully");
                System.out.println("User connected good!");

            } else {
                log.info("User not connected succefully");
                System.out.println("User not connected good!");
            }

            return ResponseEntity.ok(utilisateurPOSTDtomResult);

        }
    */
    @Override
    public ResponseEntity<?> authenticateUser(LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtsProvider.generatedJwtToken(authentication);

        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        List<String> roles = userPrinciple.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(userPrinciple.getId());
        Utilisateur utilisateur = optionalUtilisateur.get();
        UtilisateurDto utilisateurDto = UtilisateurDto.fromEntityToDto(utilisateur);
        HistoriqueLoginDto historiqueLoginDto = new HistoriqueLoginDto();
        historiqueLoginDto.setUtilisateurDto(utilisateurDto);
        historiqueLoginDto.setAction("Connection");
        historiqueLoginDto.setStatus("Valider");
        historiqueLoginDto.setCreatedDate(new Date());
        historiqueLoginService.saveHistoriqueLogin(historiqueLoginDto);

        return ResponseEntity.ok(new JwtsResponse(jwt,
                userPrinciple.getId(),
                userPrinciple.getUsername(),
                userPrinciple.getEmail(),
                roles));
    }

    /*
        @Override
        public ResponseEntity<UtilisateurPOSTDto> signUp(UtilisateurPOSTDto utilisateurPOSTDto) {
            SignUpForm signUpRequest = new SignUpForm();
            signUpRequest.setUsername(utilisateurPOSTDto.getUsername());
            signUpRequest.setEmail(utilisateurPOSTDto.getEmail());
            signUpRequest.setPassword(utilisateurPOSTDto.getPassword());

            UtilisateurPOSTDto utilisateurPOSTDtomResult = utilisateurPostService.registerUser(signUpRequest);

            if (utilisateurPOSTDtomResult != null) {
                log.info("User created succefully");
                System.out.println("User created good!");

            } else {
                log.info("User not created succefully");
                System.out.println("User not created good!");
            }

            return ResponseEntity.ok(utilisateurPOSTDtomResult);
        }
    */

    @Override
    public ResponseEntity<?> signUp(SignUpForm signUpForm) {
        if (utilisateurRepository.existsByUsername(signUpForm.getUsername())) {
            throw new ResourceNotFoundException("Fail -> Error: Username is already taken!");
        }
        if (utilisateurRepository.existsByEmail(signUpForm.getEmail())) {
            throw new ResourceNotFoundException("Error: Email is already in use!");
        }
      /*  UtilisateurPOSTDto utilisateurPOSTResult = new UtilisateurPOSTDto(
                signUpForm.getUsername(),
                signUpForm.getEmail(),
                encoder.encode(signUpForm.getPassword()));*/

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
        /*
        String[] strRoles = signUpForm.getRoles();
        Set<RoleDto> roles = new HashSet<>();
        if (strRoles == null) {
            RoleDto userRole = (RoleDto.formEntityToDto(roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."))));
            roles.add(userRole);
        }

        for (String role : strRoles) {
            switch (role.toLowerCase()) {
                case "admin":
                    RoleDto adminRole = (RoleDto.formEntityToDto(roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."))));
                    roles.add(adminRole);
                    break;

                case "manager":
                    RoleDto manager = (RoleDto.formEntityToDto(roleRepository.findByName(RoleName.ROLE_MANAGER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."))));
                    roles.add(manager);
                    break;

                case "user":
                    RoleDto userRole = (RoleDto.formEntityToDto(roleRepository.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."))));
                    roles.add(userRole);
                    break;

                default:
                    return ResponseEntity.badRequest().body("Specified role not found");

            }
        }
        */

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
        // Create new user's account
        Utilisateur utilisateur = new Utilisateur(
                signUpForm.getUsername(),
                signUpForm.getEmail(),
                encoder.encode(signUpForm.getPassword()
                )
        );
        //      Set<String> strRoles = signUpForm.getRole();
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
/*
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "manager":
                        Role modRole = roleRepository.findByName(RoleName.ROLE_MANAGER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        break;
                    default:

                        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);

                }
            });
        }*/

        utilisateur.setRoles(roles);
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

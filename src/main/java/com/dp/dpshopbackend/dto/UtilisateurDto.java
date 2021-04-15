package com.dp.dpshopbackend.dto;

import com.dp.dpshopbackend.models.Utilisateur;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class UtilisateurDto {

    private String name;

    private String username;

    private String mobile;

    private String email;

    private String photo;

    private String address;

    private boolean activated = false;

    private String password;

    private Set<RoleDto> roles = new HashSet<>();

    public UtilisateurDto fromEntityToDto(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }

        return UtilisateurDto.builder()
                .name(utilisateur.getName())
                .username(utilisateur.getUsername())
                .address(utilisateur.getAddress())
                .mobile(utilisateur.getMobile())
                .email(utilisateur.getEmail())
                .photo(utilisateur.getPhoto())
                .activated(utilisateur.isActivated())
                .password(utilisateur.getPassword())
                .build();

    }

    public Utilisateur fromDtoToEntity(UtilisateurDto utilisateurDto) {
        if (utilisateurDto == null) {
            return null;
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setName(utilisateurDto.getName());
        utilisateur.setUsername(utilisateurDto.getUsername());
        utilisateur.setAddress(utilisateurDto.getAddress());
        utilisateur.setMobile(utilisateurDto.getMobile());
        utilisateur.setEmail(utilisateurDto.getEmail());
        utilisateur.setPhoto(utilisateurDto.getPhoto());
        utilisateur.setActivated(utilisateurDto.isActivated());
        utilisateur.setPassword(utilisateurDto.getPassword());

        return utilisateur;
    }

}

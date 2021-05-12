package com.dp.dpshopbackend.dto;

import com.dp.dpshopbackend.models.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurDto {

    private long id;

    private String name;

    private String username;

    private String mobile;

    private String email;

    private String photo;

    private String address;

    private boolean activated = false;

    private String password;

    private Set<RoleDto> roles = new HashSet<>();

    public static UtilisateurDto fromEntityToDto(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }

        return UtilisateurDto.builder()
                .id(utilisateur.getId())
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

    public static Utilisateur fromDtoToEntity(UtilisateurDto utilisateurDto) {
        if (utilisateurDto == null) {
            return null;
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurDto.getId());
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

package com.dp.dpshopbackend.dto;

import com.dp.dpshopbackend.models.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    private String password;

    private String photo = "avatar.jpg";

    private Set<RoleDto> roles = new HashSet<>();

  //  private List<CommandeDto> commandeDtoList = new ArrayList<>();

    public UtilisateurDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;

    }

    public static UtilisateurDto fromEntityToDto(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }

        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .name(utilisateur.getName())
                .username(utilisateur.getUsername())
                .mobile(utilisateur.getMobile())
                .email(utilisateur.getEmail())
                .password(utilisateur.getPassword())
                .photo(utilisateur.getPhoto())
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
        utilisateur.setMobile(utilisateurDto.getMobile());
        utilisateur.setEmail(utilisateurDto.getEmail());
        utilisateur.setPassword(utilisateurDto.getPassword());
        utilisateur.setPhoto(utilisateurDto.getPhoto());
        utilisateur.setRoles(utilisateur.getRoles());

        return utilisateur;
    }


}

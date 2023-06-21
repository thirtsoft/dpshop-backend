package com.dp.dpshopbackend.dto;

import com.dp.dpshopbackend.models.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotNull(message = "Le nom utilisateur ne doit pas etre vide")
    @NotEmpty(message = "Le nom utilisateur ne doit pas etre vide")
    @NotBlank(message = "Le nom utilisateur ne doit pas etre vide")
    private String username;

    private String mobile;

    private String email;

    @NotNull(message = "Le mot de passe ne doit pas etre vide")
    @NotEmpty(message = "Le mot de passe ne doit pas etre vide")
    @NotBlank(message = "Le mot de passe ne doit pas etre vide")
    private String password;

    private String photo = "avatar.jpg";

    private Set<RoleDto> roles = new HashSet<>();

  //  private List<CommandeDto> commandeDtoList = new ArrayList<>();

    private int actif;

    public void setActif(boolean actif) {
        if (actif == true)
            this.actif = 1;
        else
            this.actif = 0;
    }

    public boolean isActif() {
        if (actif == 1)
            return true;
        else
            return false;
    }

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
                .actif(utilisateur.getActif())
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
        utilisateur.setActif(utilisateurDto.isActif());
        return utilisateur;
    }


}

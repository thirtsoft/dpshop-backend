package com.dp.dpshopbackend.dto;

import com.dp.dpshopbackend.models.Fournisseur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FournisseurDto {

    private Long id;

    private String reference;

    private String firstName;

    private String lastName;

    private String address;

    private String email;

    private String telephoneFournisseur;

    private String city;

    private String country;

    private String subject;

    private String message;

    private ArticleDto articleDto;

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

    public FournisseurDto(Long id, String reference, String firstName, String lastName,
                          String address, String email, String telephoneFournisseur,
                          String city, String country,
                         ArticleDto articleDto) {
        this.id = id;
        this.reference = reference;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.telephoneFournisseur = telephoneFournisseur;
        this.city = city;
        this.country = country;
        this.articleDto = articleDto;
    }

    public static FournisseurDto fromEntityToDto(Fournisseur fournisseur) {
        if (fournisseur == null) {
            return null;
        }

        return FournisseurDto.builder()
                .id(fournisseur.getId())
                .reference(fournisseur.getReference())
                .firstName(fournisseur.getFirstName())
                .lastName(fournisseur.getLastName())
                .address(fournisseur.getAddress())
                .telephoneFournisseur(fournisseur.getTelephoneFournisseur())
                .email(fournisseur.getEmail())
                .city(fournisseur.getCity())
                .country(fournisseur.getCountry())
                .actif(fournisseur.getActif())
                .subject(fournisseur.getSubject())
                .message(fournisseur.getMessage())
                .articleDto(ArticleDto.fromEntityToDto(fournisseur.getArticle()))
                .build();
    }

    public static Fournisseur fromDtoToEntity(FournisseurDto fournisseurDto) {
        if (fournisseurDto == null) {
            return null;
        }

        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(fournisseurDto.getId());
        fournisseur.setFirstName(fournisseurDto.getFirstName());
        fournisseur.setLastName(fournisseurDto.getLastName());
        fournisseur.setAddress(fournisseurDto.getAddress());
        fournisseur.setTelephoneFournisseur(fournisseurDto.getTelephoneFournisseur());
        fournisseur.setEmail(fournisseurDto.getEmail());
        fournisseur.setCity(fournisseurDto.getCity());
        fournisseur.setActif(fournisseurDto.isActif());
        fournisseur.setCountry(fournisseurDto.getCountry());
        fournisseur.setSubject(fournisseurDto.getSubject());
        fournisseur.setMessage(fournisseurDto.getMessage());
        fournisseur.setArticle(ArticleDto.fromDtoToEntity(fournisseurDto.getArticleDto()));

        return fournisseur;
    }
}

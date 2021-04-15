package com.dp.dpshopbackend.dto;

import com.dp.dpshopbackend.models.Fournisseur;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FournisseurDto {

    private String reference;

    private String firstName;

    private String lastName;

    private String address;

    private String email;

    private String telephoneFournisseur;

    private String city;

    private String country;

    private ProduitDto produit;

    public static FournisseurDto formEntityToDto(Fournisseur fournisseur) {
        if (fournisseur == null) {
            return null;
        }

        return FournisseurDto.builder()
                .reference(fournisseur.getReference())
                .firstName(fournisseur.getFirstName())
                .lastName(fournisseur.getLastName())
                .address(fournisseur.getAddress())
                .telephoneFournisseur(fournisseur.getTelephoneFournisseur())
                .email(fournisseur.getEmail())
                .city(fournisseur.getCity())
                .country(fournisseur.getCountry())
                .build();
    }

    public static Fournisseur fromDtoToEntity(FournisseurDto fournisseurDto) {
        if (fournisseurDto == null) {
            return null;
        }

        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setReference(fournisseurDto.getReference());
        fournisseur.setFirstName(fournisseurDto.getFirstName());
        fournisseur.setLastName(fournisseurDto.getLastName());
        fournisseur.setAddress(fournisseurDto.getAddress());
        fournisseur.setTelephoneFournisseur(fournisseurDto.getTelephoneFournisseur());
        fournisseur.setEmail(fournisseurDto.getEmail());
        fournisseur.setCity(fournisseurDto.getCity());
        fournisseur.setCountry(fournisseurDto.getCountry());

        return fournisseur;
    }
}

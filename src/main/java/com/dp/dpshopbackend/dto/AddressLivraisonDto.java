package com.dp.dpshopbackend.dto;

import com.dp.dpshopbackend.models.AddressLivraison;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressLivraisonDto {

    private String reference;

    private String quartier;

    private String phone;

    private String city;

    private String rue;

    private String country;

    private CommandeDto commande;

    public static AddressLivraisonDto fromEntityToDto(AddressLivraison addressLivraison) {
        if (addressLivraison == null) {
            return null;
        }

        return AddressLivraisonDto.builder()
                .reference(addressLivraison.getReference())
                .phone(addressLivraison.getPhone())
                .quartier(addressLivraison.getQuartier())
                .rue(addressLivraison.getRue())
                .city(addressLivraison.getCity())
                .country(addressLivraison.getCountry())
               // .commande(addressLivraison.getCommande())
                .build();
    }

    public static AddressLivraison fromDtoToEntity(AddressLivraisonDto addressClientDto) {
        if (addressClientDto == null) {
            return null;
        }

        AddressLivraison addressLivraison = new AddressLivraison();
        addressLivraison.setReference(addressClientDto.getReference());
        addressLivraison.setPhone(addressClientDto.getPhone());
        addressLivraison.setQuartier(addressClientDto.getQuartier());
        addressLivraison.setCity(addressClientDto.getCity());
        addressLivraison.setCountry(addressClientDto.getCountry());

        return addressLivraison;
    }


}

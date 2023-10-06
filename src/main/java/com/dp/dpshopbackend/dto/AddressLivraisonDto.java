package com.dp.dpshopbackend.dto;

import com.dp.dpshopbackend.models.AddressLivraison;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressLivraisonDto {

    private Long id;

    private String reference;

    private String zipcode;

    private String phone;

    private String city;

    private String rue;

    private String country;

    private StateDto stateDto;

    private int isBillingAddress;

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

    public static AddressLivraisonDto fromEntityToDto(AddressLivraison addressLivraison) {
        if (addressLivraison == null) {
            return null;
        }
        return AddressLivraisonDto.builder()
                .id(addressLivraison.getId())
                .reference(addressLivraison.getReference())
                .phone(addressLivraison.getPhone())
                .zipcode(addressLivraison.getZipcode())
                .rue(addressLivraison.getRue())
                .city(addressLivraison.getCity())
                .isBillingAddress(addressLivraison.getIsBillingAddress())
                .actif(addressLivraison.getActif())
                .country(addressLivraison.getCountry())
                .stateDto(StateDto.fromEntityToDto(addressLivraison.getState()))
                .build();
    }

    public static AddressLivraison fromDtoToEntity(AddressLivraisonDto addressClientDto) {
        if (addressClientDto == null) {
            return null;
        }
        AddressLivraison addressLivraison = new AddressLivraison();
        addressLivraison.setId(addressClientDto.getId());
        addressLivraison.setReference(addressClientDto.getReference());
        addressLivraison.setPhone(addressClientDto.getPhone());
        addressLivraison.setZipcode(addressClientDto.getZipcode());
        addressLivraison.setCity(addressClientDto.getCity());
        addressLivraison.setIsBillingAddress(addressLivraison.getIsBillingAddress());
        addressLivraison.setCountry(addressLivraison.getCountry());
        addressLivraison.setActif(addressLivraison.isActif());
        addressLivraison.setState(StateDto.fromDtoToEntity(addressClientDto.getStateDto()));
        return addressLivraison;
    }
}

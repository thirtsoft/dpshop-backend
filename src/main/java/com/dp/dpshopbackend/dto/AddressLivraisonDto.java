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

 //   private String state;

    private String rue;

    private String country;

    private StateDto stateDto;

    //  private CommandeDto commandeDto;

 //   private List<CommandeDto> commandeDtoList = new ArrayList<>();

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
       //         .state(addressLivraison.getState())
                .country(addressLivraison.getCountry())
                .stateDto(StateDto.fromEntityToDto(addressLivraison.getState()))
                //            .stateDto(StateDto.fromEntityToDto(addressLivraison.getState()))
                //        .commandeDto(CommandeDto.fromEntityToDto(addressLivraison.getCommande()))
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
      //  addressLivraison.setState(addressClientDto.getState());
        addressLivraison.setCountry(addressClientDto.getCountry());
        addressLivraison.setState(StateDto.fromDtoToEntity(addressClientDto.getStateDto()));
        //    addressLivraison.setCommande(CommandeDto.fromDtoToEntity(addressClientDto.getCommandeDto()));

        return addressLivraison;
    }


}

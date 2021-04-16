package com.dp.dpshopbackend.dto;

import com.dp.dpshopbackend.models.AddressClient;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressClientDto {

    private long id;

    private String reference;

    private String quartier;

    private String phone;

    private String city;

    private String rue;

    private String country;

    private ClientDto clientDto;

    public static AddressClientDto fromEntityToDto(AddressClient addressClient) {
        if (addressClient == null) {
            return null;
        }

        return AddressClientDto.builder()
                .id(addressClient.getId())
                .reference(addressClient.getReference())
                .quartier(addressClient.getQuartier())
                .phone(addressClient.getPhone())
                .city(addressClient.getCity())
                .rue(addressClient.getRue())
                .country(addressClient.getCountry())
                .clientDto(ClientDto.fromEntityToDto(addressClient.getClient()))
                .build();
    }

    public static AddressClient fromDtoToEntity(AddressClientDto addressClientDto) {
        if (addressClientDto == null) {
            return null;
        }

        AddressClient addressClient = new AddressClient();
        addressClient.setReference(addressClientDto.getReference());
        addressClient.setQuartier(addressClientDto.getQuartier());
        addressClient.setPhone(addressClientDto.getPhone());
        addressClient.setCity(addressClientDto.getCity());
        addressClient.setRue(addressClientDto.getRue());
        addressClient.setCountry(addressClientDto.getCountry());


        return addressClient;
    }

}

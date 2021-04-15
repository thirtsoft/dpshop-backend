package com.dp.dpshopbackend.dto;

import com.dp.dpshopbackend.models.Client;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientDto {

    private Long id;

    private String reference;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneClient;

    public ClientDto fromEntityToDto(Client client) {
        if (client == null) {
            return null;
        }
        return ClientDto.builder()
                .reference(client.getReference())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .email(client.getEmail())
                .phoneClient(client.getPhoneClient())
                .build();
    }

    public Client fromDtoToEntity(ClientDto clientDto) {
        if (clientDto == null) {
            return null;
        }

        Client client = new Client();
        client.setReference(clientDto.getReference());
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setEmail(clientDto.getEmail());
        client.setPhoneClient(clientDto.getPhoneClient());

        return client;
    }

}

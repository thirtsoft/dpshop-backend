package com.dp.dpshopbackend.dto;

import com.dp.dpshopbackend.models.Client;
import com.dp.dpshopbackend.models.Commande;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class ClientDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String mobile;

    private List<CommandeDto> commandeDtoList = new ArrayList<>();

    public ClientDto(Long id, String firstName,
                     String lastName, String email, String mobile) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
    }

    public void add(CommandeDto commandeDto) {
        if (commandeDto != null) {
            if (commandeDtoList == null) {
                commandeDtoList = new ArrayList<>();
            }
            commandeDtoList.add(commandeDto);
            commandeDto.setClientDto(this);
        }
    }

    public static ClientDto fromEntityToDto(Client client) {
        if (client == null) {
            return null;
        }
        return ClientDto.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .email(client.getEmail())
                .mobile(client.getMobile())
                .build();
    }

    public static Client fromDtoToEntity(ClientDto clientDto) {
        if (clientDto == null) {
            return null;
        }

        Client client = new Client();
        client.setId(clientDto.getId());
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setEmail(clientDto.getEmail());
        client.setMobile(clientDto.getMobile());

        return client;
    }

}

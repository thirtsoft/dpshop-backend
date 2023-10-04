package com.dp.dpshopbackend.service;

import com.dp.dpshopbackend.dto.ClientDto;
import com.dp.dpshopbackend.models.Client;
import com.dp.dpshopbackend.repository.ClientRepository;
import com.dp.dpshopbackend.services.impl.ClientServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private ClientRepository clientRepository;


    @Test
    public void findAllTest() {
        ClientDto clientDto = ClientDto.builder()
                .id(1L)
                .firstName("CLT")
                .lastName("CLT")
                .email("CLT")
                .mobile("CLT")
                .build();
        Client client = ClientDto.fromDtoToEntity(clientDto);

        when(clientRepository.findAll()).thenReturn(singletonList(client));

        List<ClientDto> clientDtoList = clientService.findAllActiveClients();

        assertThat(clientDtoList).isNotNull();
        assertThat(clientDtoList.size()).isEqualTo(1);
        verify(clientRepository).findAll();
        assertThat(clientDtoList.get(0)).isEqualTo(ClientDto.fromEntityToDto(client));
    }

    @Test
    public void findByIdTest() {
        ClientDto clientDto = ClientDto.builder()
                .id(1L)
                .firstName("CLT")
                .lastName("CLT")
                .email("CLT")
                .mobile("CLT")
                .build();
        Optional<Client> client = Optional.ofNullable(ClientDto.fromDtoToEntity(clientDto));
        when(clientRepository.findById(client.get().getId())).thenReturn(client);

        ClientDto clientDtoSavedResult = clientService.findById(clientDto.getId());

        verify(clientRepository).findById(client.get().getId());
        assertThat(clientDto).isNotNull();
        assertThat(clientDtoSavedResult).isEqualTo(clientDto);
        assertThat(clientDtoSavedResult.getId()).isEqualTo(client.get().getId());

    }


}

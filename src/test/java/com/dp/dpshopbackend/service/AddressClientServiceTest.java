package com.dp.dpshopbackend.service;

import com.dp.dpshopbackend.dto.AddressClientDto;
import com.dp.dpshopbackend.dto.ClientDto;
import com.dp.dpshopbackend.models.AddressClient;
import com.dp.dpshopbackend.repository.AddresseClientRepository;
import com.dp.dpshopbackend.services.impl.AddresseClientServiceImpl;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
public class AddressClientServiceTest {

    @InjectMocks
    private AddresseClientServiceImpl addresseClientService;

    @Mock
    private AddresseClientRepository addresseClientRepository;

    @Test
    public void CreateAddressClientTest() {
        ClientDto clientDto = ClientDto.builder()
                .id(1L)
                .reference("CLT")
                .firstName("CLT")
                .lastName("CLT")
                .build();
        AddressClientDto addressClientDto = AddressClientDto.builder()
                .id(1L)
                .reference("CLT")
                .city("city")
                .country("country")
                .clientDto(clientDto)
                .build();
        AddressClient addressClient = AddressClientDto.fromDtoToEntity(addressClientDto);
        when(addresseClientRepository.save(addressClient)).thenReturn(addressClient);

        AddressClientDto addressClientDtoSavedResult = addresseClientService.save(addressClientDto);

        verify(addresseClientRepository).save(addressClient);
        assertThat(addressClientDto).isNotNull();
        //    assertThat(addressClientDtoSavedResult).isEqualTo(addressClientDto);
        assertThat(addressClientDtoSavedResult.getId()).isEqualTo(addressClientDto.getId());
        assertThat(addressClientDtoSavedResult.getReference()).isEqualTo(addressClientDto.getReference());

    }

    @Test
    public void findAllTest() {
        ClientDto clientDto = ClientDto.builder()
                .id(1L)
                .reference("CLT")
                .firstName("CLT")
                .lastName("CLT")
                .build();
        AddressClientDto addressClientDto = AddressClientDto.builder()
                .id(1L)
                .reference("CLT")
                .city("city")
                .country("country")
                .clientDto(clientDto)
                .build();
        AddressClient addressClient = AddressClientDto.fromDtoToEntity(addressClientDto);
        when(addresseClientRepository.findAll()).thenReturn(singletonList(addressClient));

        List<AddressClientDto> addressClientDtoList = addresseClientService.findAll();

        assertThat(addressClientDtoList).isNotNull();
        assertThat(addressClientDtoList.size()).isEqualTo(1);
        verify(addresseClientRepository).findAll();
        assertThat(addressClientDtoList.get(0)).isEqualTo(AddressClientDto.fromEntityToDto(addressClient));
    }

    @Test
    public void findByIdTest() {
        ClientDto clientDto = ClientDto.builder()
                .id(1L)
                .reference("CLT")
                .firstName("CLT")
                .lastName("CLT")
                .build();
        AddressClientDto addressClientDto = AddressClientDto.builder()
                .id(1L)
                .reference("CLT")
                .city("city")
                .country("country")
                .clientDto(clientDto)
                .build();
        Optional<AddressClient> addressClient = Optional.ofNullable(AddressClientDto.fromDtoToEntity(addressClientDto));
        when(addresseClientRepository.findById(addressClient.get().getId())).thenReturn(addressClient);

        AddressClientDto addressClientDtoSavedResult = addresseClientService.findById(addressClientDto.getId());

        verify(addresseClientRepository).findById(addressClient.get().getId());
        assertThat(addressClientDto).isNotNull();
        //    assertThat(addressClientDtoSavedResult).isEqualTo(addressClientDto);
        assertThat(addressClientDtoSavedResult.getId()).isEqualTo(addressClient.get().getId());

    }


}

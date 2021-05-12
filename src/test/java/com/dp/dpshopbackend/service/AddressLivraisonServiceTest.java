package com.dp.dpshopbackend.service;

import com.dp.dpshopbackend.dto.AddressLivraisonDto;
import com.dp.dpshopbackend.dto.CommandeDto;
import com.dp.dpshopbackend.enumeration.StatusCommande;
import com.dp.dpshopbackend.models.AddressLivraison;
import com.dp.dpshopbackend.repository.AddressLivraisonRepository;
import com.dp.dpshopbackend.services.impl.AddressLivraisonServiceImpl;
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
public class AddressLivraisonServiceTest {

    @InjectMocks
    private AddressLivraisonServiceImpl addressLivraisonService;

    @Mock
    private AddressLivraisonRepository addressLivraisonRepository;

    @Test
    public void CreateAddressLivraisonTest() {
        CommandeDto commandeDto = CommandeDto.builder()
                .id(1L)
                .reference("CLT")
                .numeroCommande("Com120")
                .statusCommande(StatusCommande.LIVREE)
                .build();
        AddressLivraisonDto addressLivraisonDto = AddressLivraisonDto.builder()
                .id(1L)
                .reference("CLT")
                .city("city")
                .country("country")
                .commandeDto(commandeDto)
                .build();
        AddressLivraison addressLivraison = AddressLivraisonDto.fromDtoToEntity(addressLivraisonDto);
        when(addressLivraisonRepository.save(addressLivraison)).thenReturn(addressLivraison);

        AddressLivraisonDto addressLivraisonDtoSavedResult = addressLivraisonService.save(addressLivraisonDto);

        verify(addressLivraisonRepository).save(addressLivraison);
        assertThat(addressLivraisonDto).isNotNull();
        //    assertThat(AddressLivraisonDtoSavedResult).isEqualTo(AddressLivraisonDto);
        assertThat(addressLivraisonDtoSavedResult.getId()).isEqualTo(addressLivraisonDto.getId());
        assertThat(addressLivraisonDtoSavedResult.getReference()).isEqualTo(addressLivraisonDto.getReference());

    }

    @Test
    public void findAllTest() {
        CommandeDto commandeDto = CommandeDto.builder()
                .id(1L)
                .reference("CLT")
                .numeroCommande("Com120")
                .statusCommande(StatusCommande.LIVREE)
                .build();
        AddressLivraisonDto addressLivraisonDto = AddressLivraisonDto.builder()
                .id(1L)
                .reference("CLT")
                .city("city")
                .country("country")
                .commandeDto(commandeDto)
                .build();
        AddressLivraison addressLivraison = AddressLivraisonDto.fromDtoToEntity(addressLivraisonDto);
        when(addressLivraisonRepository.findAll()).thenReturn(singletonList(addressLivraison));

        List<AddressLivraisonDto> addressLivraisonDtoList = addressLivraisonService.findAll();

        assertThat(addressLivraisonDtoList).isNotNull();
        assertThat(addressLivraisonDtoList.size()).isEqualTo(1);
        verify(addressLivraisonRepository).findAll();
        assertThat(addressLivraisonDtoList.get(0)).isEqualTo(AddressLivraisonDto.fromEntityToDto(addressLivraison));
    }

    @Test
    public void findByIdTest() {
        CommandeDto commandeDto = CommandeDto.builder()
                .id(1L)
                .reference("CLT")
                .numeroCommande("Com120")
                .statusCommande(StatusCommande.LIVREE)
                .build();
        AddressLivraisonDto addressLivraisonDto = AddressLivraisonDto.builder()
                .id(1L)
                .reference("CLT")
                .city("city")
                .country("country")
                .commandeDto(commandeDto)
                .build();
        Optional<AddressLivraison> addressLivraison = Optional.ofNullable(AddressLivraisonDto.fromDtoToEntity(addressLivraisonDto));
        when(addressLivraisonRepository.findById(addressLivraison.get().getId())).thenReturn(addressLivraison);

        AddressLivraisonDto addressLivraisonDtoSavedResult = addressLivraisonService.findById(addressLivraisonDto.getId());

        verify(addressLivraisonRepository).findById(addressLivraison.get().getId());
        assertThat(addressLivraisonDto).isNotNull();
        //    assertThat(AddressLivraisonDtoSavedResult).isEqualTo(AddressLivraisonDto);
        assertThat(addressLivraisonDtoSavedResult.getId()).isEqualTo(addressLivraison.get().getId());

    }

}

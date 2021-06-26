package com.dp.dpshopbackend.service;

import com.dp.dpshopbackend.dto.ArticleDto;
import com.dp.dpshopbackend.dto.CommandeDto;
import com.dp.dpshopbackend.dto.LigneCommandeDto;
import com.dp.dpshopbackend.enumeration.StatusCommande;
import com.dp.dpshopbackend.models.LigneCommande;
import com.dp.dpshopbackend.repository.LigneCommandeRepository;
import com.dp.dpshopbackend.services.impl.LigneCommandeServiceImpl;
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
public class LigneCommandeServiceTest {

    @InjectMocks
    private LigneCommandeServiceImpl ligneCommandeService;

    @Mock
    private LigneCommandeRepository ligneCommandeRepository;

    @Test
    public void CreateLigneCommandeTest() {
        CommandeDto commandeDto = CommandeDto.builder()
                .id(1L)
                .reference("CLT")
                .numeroCommande(120L)
                .statusCommande(StatusCommande.PAYEE)
                .build();
        ArticleDto articleDto = ArticleDto.builder()
                .id(1L)
                .reference("Art1")
                .designation("Art1")
                .build();
        LigneCommandeDto ligneCommandeDto = LigneCommandeDto.builder()
                .id(1L)
                .quantity(2)
                .price(12000)
                .articleDto(articleDto)
                .commandeDto(commandeDto)
                .build();
        LigneCommande ligneCommande = LigneCommandeDto.fromDtoToEntity(ligneCommandeDto);
        when(ligneCommandeRepository.save(ligneCommande)).thenReturn(ligneCommande);

        LigneCommandeDto ligneCommandeDtoSavedResult = ligneCommandeService.save(ligneCommandeDto);

        verify(ligneCommandeRepository).save(ligneCommande);
        assertThat(ligneCommandeDto).isNotNull();
//        assertThat(ligneCommandeDtoSavedResult).isEqualTo(ligneCommandeDto);
        assertThat(ligneCommandeDtoSavedResult.getId()).isEqualTo(ligneCommande.getId());
        assertThat(ligneCommandeDtoSavedResult.getPrice()).isEqualTo(ligneCommande.getPrice());
        assertThat(ligneCommandeDtoSavedResult.getQuantity()).isEqualTo(ligneCommande.getQuantity());
    }

    @Test
    public void findAllTest() {
        CommandeDto commandeDto = CommandeDto.builder()
                .id(1L)
                .reference("CLT")
                .numeroCommande(120L)
                .statusCommande(StatusCommande.PAYEE)
                .build();
        ArticleDto articleDto = ArticleDto.builder()
                .id(1L)
                .reference("Art1")
                .designation("Art1")
                .build();
        LigneCommandeDto ligneCommandeDto = LigneCommandeDto.builder()
                .id(1L)
                .quantity(2)
                .price(12000)
                .articleDto(articleDto)
                .commandeDto(commandeDto)
                .build();
        LigneCommande ligneCommande = LigneCommandeDto.fromDtoToEntity(ligneCommandeDto);
        when(ligneCommandeRepository.findAll()).thenReturn(singletonList(ligneCommande));

        List<LigneCommandeDto> ligneCommandeDtoList = ligneCommandeService.findAll();

        assertThat(ligneCommandeDtoList).isNotNull();
        assertThat(ligneCommandeDtoList.size()).isEqualTo(1);
        verify(ligneCommandeRepository).findAll();
        assertThat(ligneCommandeDtoList.get(0)).isEqualTo(LigneCommandeDto.fromEntityToDto(ligneCommande));
    }

    @Test
    public void findByIdTest() {
        CommandeDto commandeDto = CommandeDto.builder()
                .id(1L)
                .reference("CLT")
                .numeroCommande(120L)
                .statusCommande(StatusCommande.PAYEE)
                .build();
        ArticleDto articleDto = ArticleDto.builder()
                .id(1L)
                .reference("Art1")
                .designation("Art1")
                .build();
        LigneCommandeDto ligneCommandeDto = LigneCommandeDto.builder()
                .id(1L)
                .quantity(2)
                .price(12000)
                .articleDto(articleDto)
                .commandeDto(commandeDto)
                .build();

        Optional<LigneCommande> ligneCommande = Optional.ofNullable(LigneCommandeDto.fromDtoToEntity(ligneCommandeDto));
        when(ligneCommandeRepository.findById(ligneCommande.get().getId())).thenReturn(ligneCommande);

        LigneCommandeDto ligneCommandeDtoSavedResult = ligneCommandeService.findById(ligneCommandeDto.getId());

        verify(ligneCommandeRepository).findById(ligneCommande.get().getId());
        assertThat(ligneCommandeDto).isNotNull();
        //      assertThat(ligneCommandeDtoSavedResult).isEqualTo(ligneCommandeDto);
        assertThat(ligneCommandeDtoSavedResult.getId()).isEqualTo(ligneCommande.get().getId());

    }


}

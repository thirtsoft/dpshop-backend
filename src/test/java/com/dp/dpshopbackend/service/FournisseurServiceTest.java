package com.dp.dpshopbackend.service;

import com.dp.dpshopbackend.dto.ArticleDto;
import com.dp.dpshopbackend.dto.FournisseurDto;
import com.dp.dpshopbackend.models.Fournisseur;
import com.dp.dpshopbackend.repository.FournisseurRepository;
import com.dp.dpshopbackend.services.impl.FournisseurServiceImpl;
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
public class FournisseurServiceTest {

    @InjectMocks
    private FournisseurServiceImpl fournisseurService;

    @Mock
    private FournisseurRepository fournisseurRepository;

    @Test
    public void CreateFournisseurTest() {
        FournisseurDto fournisseurDto = FournisseurDto.builder()
                .id(1L)
                .reference("CLT")
                .firstName("CLT")
                .lastName("CLT")
                .email("CLT")
                .telephoneFournisseur("779440310")
                .build();
        Fournisseur fournisseur = FournisseurDto.fromDtoToEntity(fournisseurDto);
        when(fournisseurRepository.save(fournisseur)).thenReturn(fournisseur);

        FournisseurDto fournisseurDtoSavedResult = fournisseurService.save(fournisseurDto);

        verify(fournisseurRepository).save(fournisseur);
        assertThat(fournisseurDto).isNotNull();
        assertThat(fournisseurDtoSavedResult).isEqualTo(fournisseurDto);
        assertThat(fournisseurDtoSavedResult.getId()).isEqualTo(fournisseur.getId());
        assertThat(fournisseurDtoSavedResult.getReference()).isEqualTo(fournisseur.getReference());
        assertThat(fournisseurDtoSavedResult.getFirstName()).isEqualTo(fournisseur.getFirstName());
    }

    @Test
    public void findAllTest() {
        FournisseurDto fournisseurDto = FournisseurDto.builder()
                .id(1L)
                .reference("CLT")
                .firstName("CLT")
                .lastName("CLT")
                .email("CLT")
                .telephoneFournisseur("779440310")
                .build();
        Fournisseur fournisseur = FournisseurDto.fromDtoToEntity(fournisseurDto);

        when(fournisseurRepository.findAll()).thenReturn(singletonList(fournisseur));

        List<FournisseurDto> fournisseurDtoList = fournisseurService.findAllActiveFournisseurs();

        assertThat(fournisseurDtoList).isNotNull();
        assertThat(fournisseurDtoList.size()).isEqualTo(1);
        verify(fournisseurRepository).findAll();
        assertThat(fournisseurDtoList.get(0)).isEqualTo(FournisseurDto.fromEntityToDto(fournisseur));
    }

    @Test
    public void findByIdTest() {
        FournisseurDto fournisseurDto = FournisseurDto.builder()
                .id(1L)
                .reference("CLT")
                .firstName("CLT")
                .lastName("CLT")
                .email("CLT")
                .telephoneFournisseur("779440310")
                .build();
        Optional<Fournisseur> fournisseur = Optional.ofNullable(FournisseurDto.fromDtoToEntity(fournisseurDto));
        when(fournisseurRepository.findById(fournisseur.get().getId())).thenReturn(fournisseur);

        FournisseurDto fournisseurDtoSavedResult = fournisseurService.findById(fournisseurDto.getId());

        verify(fournisseurRepository).findById(fournisseur.get().getId());
        assertThat(fournisseurDto).isNotNull();
        assertThat(fournisseurDtoSavedResult).isEqualTo(fournisseurDto);
        assertThat(fournisseurDtoSavedResult.getId()).isEqualTo(fournisseur.get().getId());

    }


}

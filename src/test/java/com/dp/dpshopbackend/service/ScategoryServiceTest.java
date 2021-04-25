package com.dp.dpshopbackend.service;

import com.dp.dpshopbackend.dto.CategorieDto;
import com.dp.dpshopbackend.dto.ScategorieDto;
import com.dp.dpshopbackend.models.Categorie;
import com.dp.dpshopbackend.models.Scategorie;
import com.dp.dpshopbackend.repository.CategorieRepository;
import com.dp.dpshopbackend.repository.ScategorieRepository;
import com.dp.dpshopbackend.services.impl.CategorieServiceImpl;
import com.dp.dpshopbackend.services.impl.ScategorieServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScategoryServiceTest {

    @InjectMocks
    private ScategorieServiceImpl scategorieService;

    @Mock
    private ScategorieRepository scategorieRepository;

    @Test
    public void CreateScategoryTest() {
        CategorieDto categoryDto = new CategorieDto(1L, "cat","cat");
        ScategorieDto scategorieDto = ScategorieDto.builder()
                .id(1L)
                .code("123")
                .libelle("libelle")
                .categorieDto(categoryDto)
                .build();
        Scategorie scategorie = ScategorieDto.fromDtoToEntity(scategorieDto);
        when(scategorieRepository.save(scategorie)).thenReturn(scategorie);

        ScategorieDto scategoryDtoSavedResult = scategorieService.save(scategorieDto);

        verify(scategorieRepository).save(scategorie);
        assertThat(scategorieDto).isNotNull();
        assertThat(scategoryDtoSavedResult).isEqualTo(scategorieDto);
        assertThat(scategoryDtoSavedResult.getId()).isEqualTo(scategorie.getId());
        assertThat(scategoryDtoSavedResult.getCode()).isEqualTo(scategorie.getCode());
        assertThat(scategoryDtoSavedResult.getLibelle()).isEqualTo(scategorie.getLibelle());
    }

    @Test
    public void findAllTest() {
        CategorieDto categoryDto = new CategorieDto(1L, "cat","cat");
        ScategorieDto scategorieDto = ScategorieDto.builder()
                .id(1L)
                .code("Mobile")
                .libelle("Samsung A10s")
                .categorieDto(categoryDto)
                .build();

        Scategorie scategorie = ScategorieDto.fromDtoToEntity(scategorieDto);
        when(scategorieRepository.findAll()).thenReturn(singletonList(scategorie));

        List<ScategorieDto> scategories = scategorieService.findAll();

        assertThat(scategories).isNotNull();
        assertThat(scategories.size()).isEqualTo(1);
        verify(scategorieRepository).findAll();
        assertThat(scategories.get(0)).isEqualTo(ScategorieDto.fromEntityToDto(scategorie));
    }

    @Test
    public void findByIdTest() {
        CategorieDto categoryDto = new CategorieDto(1L, "cat","cat");
        ScategorieDto scategorieDto = ScategorieDto.builder()
                .id(1L)
                .code("123")
                .libelle("Libelle")
                .categorieDto(categoryDto)
                .build();
        Optional<Scategorie>  scategorie = Optional.ofNullable(ScategorieDto.fromDtoToEntity(scategorieDto));
        when(scategorieRepository.findById(scategorie.get().getId())).thenReturn(scategorie);

        ScategorieDto scategoryDtoSavedResult = scategorieService.findById(scategorieDto.getId());

        verify(scategorieRepository).findById(scategorie.get().getId());
        assertThat(scategorieDto).isNotNull();
        assertThat(scategoryDtoSavedResult).isEqualTo(scategorieDto);
        assertThat(scategoryDtoSavedResult.getId()).isEqualTo(scategorie.get().getId());

    }


}

package com.dp.dpshopbackend.service;

import com.dp.dpshopbackend.dto.CategorieDto;
import com.dp.dpshopbackend.models.Categorie;
import com.dp.dpshopbackend.repository.CategorieRepository;
import com.dp.dpshopbackend.services.impl.CategorieServiceImpl;
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
public class AddressLivraisonServiceTest {

    @InjectMocks
    private CategorieServiceImpl categorieService;

    @Mock
    private CategorieRepository categorieRepository;

    @Test
    public void CreateCategoryTest() {
        CategorieDto categorieDto = CategorieDto.builder()
                .id(1L)
                .code("123")
                .designation("designation")
                .build();
        Categorie categorie = CategorieDto.fromDtoToEntity(categorieDto);
        when(categorieRepository.save(categorie)).thenReturn(categorie);

        CategorieDto categoryDtoSavedResult = categorieService.save(categorieDto);

        verify(categorieRepository).save(categorie);
        assertThat(categorieDto).isNotNull();
        assertThat(categoryDtoSavedResult).isEqualTo(categorieDto);
        assertThat(categoryDtoSavedResult.getId()).isEqualTo(categorie.getId());
        assertThat(categoryDtoSavedResult.getCode()).isEqualTo(categorie.getCode());
        assertThat(categoryDtoSavedResult.getDesignation()).isEqualTo(categorie.getDesignation());
    }

    @Test
    public void findAllTest() {
        CategorieDto categorieDto = new CategorieDto();
        categorieDto.setId(1L);
        categorieDto.setCode("Mobile");
        categorieDto.setDesignation("Samsung A10s");

        Categorie categorie = CategorieDto.fromDtoToEntity(categorieDto);
        when(categorieRepository.findAll()).thenReturn(singletonList(categorie));

        List<CategorieDto> categories = categorieService.findAll();

        assertThat(categories).isNotNull();
        assertThat(categories.size()).isEqualTo(1);
        verify(categorieRepository).findAll();
        assertThat(categories.get(0)).isEqualTo(CategorieDto.fromEntityToDto(categorie));
    }

    @Test
    public void findByIdTest() {
        CategorieDto categorieDto = CategorieDto.builder()
                .id(1L)
                .code("123")
                .designation("designation")
                .build();
        Optional<Categorie>  categorie = Optional.ofNullable(CategorieDto.fromDtoToEntity(categorieDto));
        when(categorieRepository.findById(categorie.get().getId())).thenReturn(categorie);

        CategorieDto categoryDtoSavedResult = categorieService.findById(categorieDto.getId());

        verify(categorieRepository).findById(categorie.get().getId());
        assertThat(categorieDto).isNotNull();
        assertThat(categoryDtoSavedResult).isEqualTo(categorieDto);
        assertThat(categoryDtoSavedResult.getId()).isEqualTo(categorie.get().getId());

    }

    @Test
    public void findByDesignationTest() {
        CategorieDto categorieDto = CategorieDto.builder()
                .id(1L)
                .code("123")
                .designation("designation")
                .build();
        Optional<Categorie>  categorie = Optional.ofNullable(CategorieDto.fromDtoToEntity(categorieDto));
        when(categorieRepository.findCategorieByDesignation(categorie.get().getDesignation())).thenReturn(categorie);

        CategorieDto categoryDtoSavedResult = categorieService.findByDesignation(categorieDto.getDesignation());

        assertNotNull(categorieDto);
        verify(categorieRepository).findCategorieByDesignation(categorie.get().getDesignation());
        assertThat(categorieDto).isNotNull();
        assertThat(categoryDtoSavedResult).isEqualTo(categorieDto);
        assertThat(categoryDtoSavedResult.getDesignation()).isEqualTo(categorie.get().getDesignation());

    }


}

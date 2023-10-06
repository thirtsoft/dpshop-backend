package com.dp.dpshopbackend.service;

import com.dp.dpshopbackend.dto.CategoryDto;
import com.dp.dpshopbackend.dto.ScategoryDto;
import com.dp.dpshopbackend.models.Scategory;
import com.dp.dpshopbackend.repository.ScategoryRepository;
import com.dp.dpshopbackend.services.impl.ScategoryServiceImpl;
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
public class ScategoryServiceTest {

    @InjectMocks
    private ScategoryServiceImpl scategorieService;

    @Mock
    private ScategoryRepository scategoryRepository;

    @Test
    public void CreateScategoryTest() {
      //  CategoryDto categoryDto = new CategoryDto(1L, "cat", "cat");
        CategoryDto categoryDto = new CategoryDto();
        ScategoryDto scategoryDto = ScategoryDto.builder()
                .id(1L)
                .code("123")
                .libelle("libelle")
                .categoryDto(categoryDto)
                .build();
        Scategory scategory = ScategoryDto.fromDtoToEntity(scategoryDto);
        when(scategoryRepository.save(scategory)).thenReturn(scategory);

        ScategoryDto scategoryDtoSavedResult = scategorieService.save(scategoryDto);

        verify(scategoryRepository).save(scategory);
        assertThat(scategoryDto).isNotNull();
        assertThat(scategoryDtoSavedResult).isEqualTo(scategoryDto);
        assertThat(scategoryDtoSavedResult.getId()).isEqualTo(scategory.getId());
        assertThat(scategoryDtoSavedResult.getCode()).isEqualTo(scategory.getCode());
        assertThat(scategoryDtoSavedResult.getLibelle()).isEqualTo(scategory.getLibelle());
    }

    @Test
    public void findAllTest() {
      //  CategoryDto categoryDto = new CategoryDto(1L, "cat", "cat");
        CategoryDto categoryDto = new CategoryDto();
        ScategoryDto scategoryDto = ScategoryDto.builder()
                .id(1L)
                .code("Mobile")
                .libelle("Samsung A10s")
                .categoryDto(categoryDto)
                .build();

        Scategory scategory = ScategoryDto.fromDtoToEntity(scategoryDto);
        when(scategoryRepository.findAll()).thenReturn(singletonList(scategory));

        List<ScategoryDto> scategories = scategorieService.findAllActiveSubcategories();

        assertThat(scategories).isNotNull();
        assertThat(scategories.size()).isEqualTo(1);
        verify(scategoryRepository).findAll();
        assertThat(scategories.get(0)).isEqualTo(ScategoryDto.fromEntityToDto(scategory));
    }

    @Test
    public void findByIdTest() {
      //  CategoryDto categoryDto = new CategoryDto(1L, "cat", "cat");
        CategoryDto categoryDto = new CategoryDto();
        ScategoryDto scategoryDto = ScategoryDto.builder()
                .id(1L)
                .code("123")
                .libelle("Libelle")
                .categoryDto(categoryDto)
                .build();
        Optional<Scategory> scategorie = Optional.ofNullable(ScategoryDto.fromDtoToEntity(scategoryDto));
        when(scategoryRepository.findById(scategorie.get().getId())).thenReturn(scategorie);

        ScategoryDto scategoryDtoSavedResult = scategorieService.findById(scategoryDto.getId());

        verify(scategoryRepository).findById(scategorie.get().getId());
        assertThat(scategoryDto).isNotNull();
        assertThat(scategoryDtoSavedResult).isEqualTo(scategoryDto);
        assertThat(scategoryDtoSavedResult.getId()).isEqualTo(scategorie.get().getId());

    }


}

package com.dp.dpshopbackend.service;

import com.dp.dpshopbackend.dto.CategorieDto;
import com.dp.dpshopbackend.repository.CategorieRepository;
import com.dp.dpshopbackend.services.CategorieService;
import com.dp.dpshopbackend.services.impl.CategorieServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import static org.mockito.Mockito.doReturn;

//@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {
/*
    @InjectMocks
    private CategorieServiceImpl categorieService;

    @Mock
    private CategorieRepository categorieRepository;
/*
 /*
    @Test
    public void testCreateCategory() {
        String code = "sac";
        String designation = "Sac à main";
        CategorieDto categorieDto = new CategorieDto();
        categorieDto.setCode(code); categorieDto.setDesignation(designation);

  //      doReturn(categorieDto).when(CategorieDto.fromEntityToDto(categorieRepository.save(CategorieDto.fromDtoToEntity(categorieDto))));

    //    CategorieDto categoryDtoSaved = categorieService.save(categorieDto);

        // Assert the response
      //  assertNotNull(categorieDto);
      //  assertThat(categoryDtoSaved).isEqualTo(categorieDto);

        when(CategorieDto.fromEntityToDto(categorieRepository.save(CategorieDto.fromDtoToEntity(categorieDto)))).thenReturn(categorieDto);

        CategorieDto categoryDtoSavedResult = categorieService.save(categorieDto);

/*
        CategorieDto categoryDtoResult = CategorieDto.fromEntityToDto(
               when(categorieRepository.save(
                        CategorieDto.fromDtoToEntity(categorieDto)
                )
        ).thenReturn(categoryDtoResult));
        */
    /*
        assertNotNull(categorieDto);
        assertThat(categoryDtoSavedResult).isEqualTo(categorieDto);

    }
    */

}

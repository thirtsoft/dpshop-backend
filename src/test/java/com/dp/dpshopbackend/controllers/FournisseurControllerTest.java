package com.dp.dpshopbackend.controllers;

import com.dp.dpshopbackend.controller.FournisseurController;
import com.dp.dpshopbackend.dto.ArticleDto;
import com.dp.dpshopbackend.dto.CategoryDto;
import com.dp.dpshopbackend.dto.FournisseurDto;
import com.dp.dpshopbackend.dto.ScategoryDto;
import com.dp.dpshopbackend.services.ArticleService;
import com.dp.dpshopbackend.services.FournisseurService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.dp.dpshopbackend.utils.Constants.APP_ROOT;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FournisseurControllerTest {

    String uri = APP_ROOT + "/fournisseurs/all";

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ArticleService articleService;

    @Mock
    private FournisseurService fournisseurService;

    @InjectMocks
    private FournisseurController fournisseurController;

    private CategoryDto categoryDto;
    private ScategoryDto scategoryDto;
    private ArticleDto articleDto;
    private FournisseurDto fournisseurDto;

    private List<FournisseurDto> fournisseurDtoList;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setup() {
        scategoryDto = new ScategoryDto();
        articleDto = new ArticleDto(1L, "prod1", "prod1", 150, 1700.0, 1800.0, true, true, "prod1", "photo", scategoryDto);
        fournisseurDto = new FournisseurDto(1L, "f1", "f1", "f1", "f1", "f1", "f1", "f1", "f1");

        mockMvc = MockMvcBuilders.standaloneSetup(fournisseurController).build();
    }

    @After
    public void tearDown() {
        fournisseurDto = null;
    }

    @Test
    public void PostMappingOfFournisseur() throws Exception {
        when(fournisseurService.save(any())).thenReturn(fournisseurDto);
        mockMvc.perform(post("/shop-mania/v1/fournisseurs/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(fournisseurDto)))
                .andExpect(status().isOk());
        verify(fournisseurService, times(1)).save(any());
    }

    @Test
    public void GetMappingOfAllFournisseurs() throws Exception {
        when(fournisseurService.findAllActiveFournisseurs()).thenReturn(fournisseurDtoList);
        mockMvc.perform(get("/shop-mania/v1/fournisseurs/search-all-active-fournisseurs").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(fournisseurDto))).
                andDo(MockMvcResultHandlers.print());
        verify(fournisseurService).findAllActiveFournisseurs();
        verify(fournisseurService, times(1)).findAllActiveFournisseurs();
    }

    @Test
    public void GetMappingOfFournisseurShouldReturnRespectiveFournisseur() throws Exception {
        Long fourID = (long) 1;
        when(fournisseurService.findById(fournisseurDto.getId())).thenReturn(fournisseurDto);
        mockMvc.perform(get("/shop-mania/v1/fournisseurs/findById/" + fourID).
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(fournisseurDto))).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print());
    }

}

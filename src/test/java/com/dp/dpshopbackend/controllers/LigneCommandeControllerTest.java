package com.dp.dpshopbackend.controllers;

import com.dp.dpshopbackend.controller.LigneCommandeController;
import com.dp.dpshopbackend.dto.ArticleDto;
import com.dp.dpshopbackend.dto.CommandeDto;
import com.dp.dpshopbackend.dto.LigneCommandeDto;
import com.dp.dpshopbackend.services.ArticleService;
import com.dp.dpshopbackend.services.CommandeService;
import com.dp.dpshopbackend.services.LigneCommandeService;
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
public class LigneCommandeControllerTest {

    String uri = APP_ROOT + "/lignecommandes/all";

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CommandeService commandeService;

    @Mock
    private ArticleService articleService;

    @Mock
    private LigneCommandeService ligneCommandeService;

    @InjectMocks
    private LigneCommandeController ligneCommandeController;

    private CommandeDto commandeDto;
    private ArticleDto articleDto;

    private LigneCommandeDto ligneCommandeDto;

    private List<LigneCommandeDto> ligneCommandeDtoList;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setup() {
        articleDto = new ArticleDto();
        articleDto.setReference("prod");
        articleDto.setDesignation("prod1");
        commandeDto = new CommandeDto();
        commandeDto.setNumeroCommande(120L);
     //   ligneCommandeDto = new LigneCommandeDto(1L, 10L, 12, 12500, 1L, "Product-1", commandeDto, articleDto);
        ligneCommandeDto = new LigneCommandeDto();

        mockMvc = MockMvcBuilders.standaloneSetup(ligneCommandeController).build();
    }

    @After
    public void tearDown() {
        ligneCommandeDto = null;
    }

    @Test
    public void PostMappingOfLigneCommande() throws Exception {
        when(ligneCommandeService.save(any())).thenReturn(ligneCommandeDto);
        mockMvc.perform(post("/shop-mania/v1/lignecommandes/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(ligneCommandeDto)))
                .andExpect(status().isOk());
        verify(ligneCommandeService, times(1)).save(any());
    }

    @Test
    public void GetMappingOfAllArticles() throws Exception {
        when(ligneCommandeService.findAll()).thenReturn(ligneCommandeDtoList);
        mockMvc.perform(get("/shop-mania/v1/lignecommandes/all").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(ligneCommandeDto))).
                andDo(MockMvcResultHandlers.print());
        verify(ligneCommandeService).findAll();
        verify(ligneCommandeService, times(1)).findAll();
    }

    @Test
    public void GetMappingOfArticleShouldReturnRespectiveArticle() throws Exception {
        Long artID = (long) 1;
        when(ligneCommandeService.findById(ligneCommandeDto.getId())).thenReturn(ligneCommandeDto);
        mockMvc.perform(get("/shop-mania/v1/lignecommandes/" + artID).
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(ligneCommandeDto))).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print());
    }


}

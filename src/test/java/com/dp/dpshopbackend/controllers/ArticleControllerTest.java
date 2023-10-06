package com.dp.dpshopbackend.controllers;

import com.dp.dpshopbackend.controller.ArticleController;
import com.dp.dpshopbackend.dto.ArticleDto;
import com.dp.dpshopbackend.dto.CategoryDto;
import com.dp.dpshopbackend.dto.ScategoryDto;
import com.dp.dpshopbackend.services.ArticleService;
import com.dp.dpshopbackend.services.ScategoryService;
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
public class ArticleControllerTest {

    String uri = APP_ROOT + "/categories/all";

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ScategoryService scategoryService;

    @Mock
    private ArticleService articleService;

    @InjectMocks
    private ArticleController articleController;

    private CategoryDto categoryDto;
    private ScategoryDto scategoryDto;
    private ArticleDto articleDto;

    private List<ArticleDto> articleDtoList;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setup() {
        /*
        categoryDto = new CategoryDto(1L, "PC", "PC");
        scategoryDto = new ScategoryDto(1L, "HP", "HP ProBooks", categoryDto);*/
        scategoryDto = new ScategoryDto();
        articleDto = new ArticleDto(1L, "prod1", "prod1", 150, 1700.0, 1800.0, true, true, "prod1", "photo", scategoryDto);

        mockMvc = MockMvcBuilders.standaloneSetup(articleController).build();
    }

    @After
    public void tearDown() {
        articleDto = null;
    }

    @Test
    public void PostMappingOfArticle() throws Exception {
        when(articleService.save(any())).thenReturn(articleDto);
        mockMvc.perform(post("/shop-mania/v1/articles/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(articleDto)))
                .andExpect(status().isOk());
        verify(articleService, times(1)).save(any());
    }

    @Test
    public void GetMappingOfAllArticles() throws Exception {
        when(articleService.findAllActiveArticles()).thenReturn(articleDtoList);
        mockMvc.perform(get("/shop-mania/v1/articles/search-all-active-articles").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(articleDto))).
                andDo(MockMvcResultHandlers.print());
        verify(articleService).findAllActiveArticles();
        verify(articleService, times(1)).findAllActiveArticles();
    }

    @Test
    public void GetMappingOfArticleShouldReturnRespectiveArticle() throws Exception {
        Long artID = (long) 1;
        when(articleService.findById(articleDto.getId())).thenReturn(articleDto);
        mockMvc.perform(get("/shop-mania/v1/articles/" + artID).
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(articleDto))).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void GetMappingOfArticleByReferenceShouldReturnRespectiveArticle() throws Exception {
        String reference = "prod1";
        when(articleService.findById(scategoryDto.getId())).thenReturn(articleDto);
        mockMvc.perform(get("/shop-mania/v1/articles/search-by-reference/" + reference).
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(articleDto))).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print());
    }

}

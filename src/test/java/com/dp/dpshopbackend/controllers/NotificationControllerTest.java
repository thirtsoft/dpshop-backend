package com.dp.dpshopbackend.controllers;

import com.dp.dpshopbackend.controller.NotificationController;
import com.dp.dpshopbackend.dto.ArticleDto;
import com.dp.dpshopbackend.dto.NotificationDto;
import com.dp.dpshopbackend.dto.UtilisateurDto;
import com.dp.dpshopbackend.services.ArticleService;
import com.dp.dpshopbackend.services.NotificationService;
import com.dp.dpshopbackend.services.UtilisateurService;
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
public class NotificationControllerTest {

    String uri = APP_ROOT + "/categories/all";

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ArticleService articleService;

    @Mock
    private UtilisateurService utilisateurService;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private NotificationController notificationController;

    private UtilisateurDto utilisateurDto;
    private ArticleDto articleDto;
    private NotificationDto notificationDto;

    private List<NotificationDto> notificationDtoList;

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
        articleDto.setReference("art1");
        articleDto.setDesignation("art1");
        utilisateurDto = new UtilisateurDto();
        utilisateurDto.setName("tairou");
        utilisateurDto.setUsername("thir");
        //     notificationDto = new NotificationDto(1L, "note1", "note1", "note1", articleDto, utilisateurDto);
        notificationDto = new NotificationDto(1L, "note1", "note1", "note1", articleDto);

        mockMvc = MockMvcBuilders.standaloneSetup(notificationController).build();
    }

    @After
    public void tearDown() {
        notificationDto = null;
    }

    @Test
    public void PostMappingOfNotification() throws Exception {
        when(notificationService.save(any())).thenReturn(notificationDto);
        mockMvc.perform(post("/shop-mania/v1/notifications/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(notificationDto)))
                .andExpect(status().isOk());
        verify(notificationService, times(1)).save(any());
    }

    @Test
    public void GetMappingOfAllNotifications() throws Exception {
        when(notificationService.findAll()).thenReturn(notificationDtoList);
        mockMvc.perform(get("/shop-mania/v1/notifications/all").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(notificationDto))).
                andDo(MockMvcResultHandlers.print());
        verify(notificationService).findAll();
        verify(notificationService, times(1)).findAll();
    }

    @Test
    public void GetMappingOfNotificationShouldReturnRespectiveNotification() throws Exception {
        Long artID = (long) 1;
        when(notificationService.findById(notificationDto.getId())).thenReturn(notificationDto);
        mockMvc.perform(get("/shop-mania/v1/notifications/" + artID).
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(notificationDto))).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print());
    }


}

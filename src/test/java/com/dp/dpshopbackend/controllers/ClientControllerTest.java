package com.dp.dpshopbackend.controllers;

import com.dp.dpshopbackend.controller.ClientController;
import com.dp.dpshopbackend.dto.ClientDto;
import com.dp.dpshopbackend.services.ClientService;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

    String uri = APP_ROOT + "/clients/all";

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    private ClientDto clientDto;
    private List<ClientDto> clientDtoList;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setup() {
        clientDto = new ClientDto(1L, "cl1", "cl1", "cl1", "cl1");
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

    @After
    public void tearDown() {
        clientDto = null;
    }

    @Test
    public void GetMappingOfAllClients() throws Exception {
        when(clientService.findAllActiveClients()).thenReturn(clientDtoList);
        mockMvc.perform(get("/shop-mania/v1/clients/search-all-active-clients").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(clientDto))).
                andDo(MockMvcResultHandlers.print());
        verify(clientService).findAllActiveClients();
        verify(clientService, times(1)).findAllActiveClients();
    }

    @Test
    public void GetMappingOfClientShouldReturnRespectiveClient() throws Exception {
        Long clientID = (long) 1;
        when(clientService.findById(clientDto.getId())).thenReturn(clientDto);
        mockMvc.perform(get("/shop-mania/v1/clients/findById/" + clientID).
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(clientDto))).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print());
    }


}

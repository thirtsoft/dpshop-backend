package com.dp.dpshopbackend.controllers;

import com.dp.dpshopbackend.controller.AddressLivraisonController;
import com.dp.dpshopbackend.dto.AddressLivraisonDto;
import com.dp.dpshopbackend.dto.CommandeDto;
import com.dp.dpshopbackend.services.AddressLivraisonService;
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
public class AddressLivraisonControllerTest {

    String uri = APP_ROOT + "/addresslivraisons/all";

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AddressLivraisonService addressLivraisonService;

    @InjectMocks
    private AddressLivraisonController addressLivraisonController;

    private CommandeDto commandeDto;
    private AddressLivraisonDto addressLivraisonDto;
    private List<AddressLivraisonDto> addressLivraisonDtoList;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setup() {
        commandeDto = new CommandeDto();
        commandeDto.setNumeroCommande(120L);
        addressLivraisonDto = new AddressLivraisonDto();
        addressLivraisonDto.setReference("aaa");
        addressLivraisonDto.setCity("ccc");
        //    addressLivraisonDto.setCommandeDto(commandeDto);

        mockMvc = MockMvcBuilders.standaloneSetup(addressLivraisonController).build();
    }

    @After
    public void tearDown() {
        addressLivraisonDto = null;
    }

    @Test
    public void PostMappingOfAddressLivraison() throws Exception {
        when(addressLivraisonService.save(any())).thenReturn(addressLivraisonDto);
        mockMvc.perform(post("/shop-mania/v1/addresslivraisons/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(addressLivraisonDto)))
                .andExpect(status().isOk());
        verify(addressLivraisonService, times(1)).save(any());
    }

    @Test
    public void GetMappingOfAllAddressLivraison() throws Exception {
        when(addressLivraisonService.findAll()).thenReturn(addressLivraisonDtoList);
        mockMvc.perform(get("/shop-mania/v1/addresslivraisons/search-all-active-addresslivraisons").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(addressLivraisonDto))).
                andDo(MockMvcResultHandlers.print());
        verify(addressLivraisonService).findAll();
        verify(addressLivraisonService, times(1)).findAll();
    }

    @Test
    public void GetMappingOfAddressLivraisonShouldReturnRespectiveAddressLivraison() throws Exception {
        Long artID = (long) 1;
        when(addressLivraisonService.findById(addressLivraisonDto.getId())).thenReturn(addressLivraisonDto);
        mockMvc.perform(get("/shop-mania/v1/addresslivraisons/" + artID).
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(addressLivraisonDto))).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print());
    }


}

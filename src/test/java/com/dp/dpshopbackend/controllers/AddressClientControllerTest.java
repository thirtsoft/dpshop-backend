package com.dp.dpshopbackend.controllers;

import com.dp.dpshopbackend.controller.AddresseClientController;
import com.dp.dpshopbackend.dto.AddressClientDto;
import com.dp.dpshopbackend.dto.ClientDto;
import com.dp.dpshopbackend.services.AddresseClientService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AddressClientControllerTest {

    String uri = APP_ROOT + "/categories/all";

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ClientService clientService;

    @Mock
    private AddresseClientService addresseClientService;

    @InjectMocks
    private AddresseClientController addresseClientController;

    private ClientDto clientDto;
    private AddressClientDto addressClientDto;

    private List<AddressClientDto> addressClientDtoList;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setup() {
        clientDto = new ClientDto(1L, "cl1", "cl1", "cl1", "cl1", "cl1");
        addressClientDto = new AddressClientDto();
        addressClientDto.setId(1L);
        addressClientDto.setCity("Dakar");
        addressClientDto.setReference("Add");
        addressClientDto.setClientDto(clientDto);

        mockMvc = MockMvcBuilders.standaloneSetup(addresseClientController).build();
    }

    @After
    public void tearDown() {
        addressClientDto = null;
    }

    @Test
    public void PostMappingOfAddressClient() throws Exception {
        when(addresseClientService.save(any())).thenReturn(addressClientDto);
        mockMvc.perform(post("/shop-mania/v1/addresseclients/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(addressClientDto)))
                .andExpect(status().isOk());
        verify(addresseClientService, times(1)).save(any());
    }

    @Test
    public void GetMappingOfAllAddressClients() throws Exception {
        when(addresseClientService.findAll()).thenReturn(addressClientDtoList);
        mockMvc.perform(get("/shop-mania/v1/addresseclients/all").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(addressClientDto))).
                andDo(MockMvcResultHandlers.print());
        verify(addresseClientService).findAll();
        verify(addresseClientService, times(1)).findAll();
    }

    @Test
    public void GetMappingOfAddressClientShouldReturnRespectiveAddressClient() throws Exception {
        Long artID = (long) 1;
        when(addresseClientService.findById(addressClientDto.getId())).thenReturn(addressClientDto);
        mockMvc.perform(get("/shop-mania/v1/addresseclients/" + artID).
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(addressClientDto))).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andDo(MockMvcResultHandlers.print());
    }

}

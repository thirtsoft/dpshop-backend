package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.AddresseClientApi;
import com.dp.dpshopbackend.dto.AddressClientDto;
import com.dp.dpshopbackend.services.AddresseClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddresseClientController implements AddresseClientApi {

    private AddresseClientService addresseClientService;

    @Autowired
    public AddresseClientController(AddresseClientService addresseClientService) {
        this.addresseClientService = addresseClientService;
    }

    @Override
    public ResponseEntity<AddressClientDto> save(AddressClientDto addressClientDto) {
        return ResponseEntity.ok(addresseClientService.save(addressClientDto));
    }

    @Override
    public ResponseEntity<AddressClientDto> findById(Long id) {
        return ResponseEntity.ok(addresseClientService.findById(id));
    }

    @Override
    public List<AddressClientDto> findAll() {
        return addresseClientService.findAll();
    }

    @Override
    public void delete(Long id) {
        addresseClientService.delete(id);
    }
}

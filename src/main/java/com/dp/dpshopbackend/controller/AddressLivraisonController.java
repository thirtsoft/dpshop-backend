package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.AddressLivraisonApi;
import com.dp.dpshopbackend.dto.AddressLivraisonDto;
import com.dp.dpshopbackend.services.AddressLivraisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressLivraisonController implements AddressLivraisonApi {

    private AddressLivraisonService addressLivraisonService;

    @Autowired
    public AddressLivraisonController(AddressLivraisonService addressLivraisonService) {
        this.addressLivraisonService = addressLivraisonService;
    }

    @Override
    public ResponseEntity<AddressLivraisonDto> save(AddressLivraisonDto addressLivraisonDto) {
        return ResponseEntity.ok(addressLivraisonService.save(addressLivraisonDto));
    }

    @Override
    public ResponseEntity<AddressLivraisonDto> findById(Long id) {
        return ResponseEntity.ok(addressLivraisonService.findById(id));
    }

    @Override
    public List<AddressLivraisonDto> findAll() {
        return addressLivraisonService.findAll();
    }

    @Override
    public void delete(Long id) {
        addressLivraisonService.delete(id);
    }
}

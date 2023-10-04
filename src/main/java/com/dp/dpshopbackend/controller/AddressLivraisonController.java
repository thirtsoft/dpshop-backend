package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.AddressLivraisonApi;
import com.dp.dpshopbackend.dto.AddressLivraisonDto;
import com.dp.dpshopbackend.services.AddressLivraisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressLivraisonController implements AddressLivraisonApi {

    private final AddressLivraisonService addressLivraisonService;

    @Autowired
    public AddressLivraisonController(AddressLivraisonService addressLivraisonService) {
        this.addressLivraisonService = addressLivraisonService;
    }

    @Override
    public ResponseEntity<AddressLivraisonDto> findById(Long id) {
        return ResponseEntity.ok(addressLivraisonService.findById(id));
    }

    @Override
    public ResponseEntity<List<AddressLivraisonDto>> getAllActiveAddressLivraisons() {
        List<AddressLivraisonDto> addressLivraisonDtos = addressLivraisonService.findAllActiveAddressLivraisons();
        return new ResponseEntity<>(addressLivraisonDtos, HttpStatus.OK);
    }

    @Override
    public void deleteAddressLivraison(Long idAddressLivraison) {
        addressLivraisonService.deleteAddressLivraison(idAddressLivraison);
    }
}

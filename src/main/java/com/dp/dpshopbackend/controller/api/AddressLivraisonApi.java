package com.dp.dpshopbackend.controller.api;

import com.dp.dpshopbackend.dto.AddressLivraisonDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dp.dpshopbackend.utils.Constants.APP_ROOT;

public interface AddressLivraisonApi {

    @PostMapping(value = APP_ROOT + "/addresslivraisons/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AddressLivraisonDto> save(@RequestBody AddressLivraisonDto addressLivraisonDto);

    @GetMapping(value = APP_ROOT + "/addresslivraisons/{idAddressLivraison}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AddressLivraisonDto> findById(@PathVariable("idAddressLivraison") Long id);

    @GetMapping(value = APP_ROOT + "/addresslivraisons/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<AddressLivraisonDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/addresslivraisons/delete/{idAddressLivraison}")
    void delete(@PathVariable("idAddressLivraison") Long id);
}

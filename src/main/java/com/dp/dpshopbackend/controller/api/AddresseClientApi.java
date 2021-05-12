package com.dp.dpshopbackend.controller.api;

import com.dp.dpshopbackend.dto.AddressClientDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dp.dpshopbackend.utils.Constants.APP_ROOT;

public interface AddresseClientApi {

    @PostMapping(value = APP_ROOT + "/addresseclients/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AddressClientDto> save(@RequestBody AddressClientDto addressClientDto);

    @GetMapping(value = APP_ROOT + "/addresseclients/{idAddressClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AddressClientDto> findById(@PathVariable("idAddressClient") Long id);

    @GetMapping(value = APP_ROOT + "/addresseclients/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<AddressClientDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/addresseclients/delete/{idAddressClient}")
    void delete(@PathVariable("idAddressClient") Long id);
}

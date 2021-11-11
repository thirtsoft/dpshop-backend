package com.dp.dpshopbackend.controller.api;

import com.dp.dpshopbackend.dto.AddressClientDto;
import com.dp.dpshopbackend.dto.ArticleDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @GetMapping(value = APP_ROOT + "/addresseclients/searchAllAddressClientsOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des AddressClients par ordre descroissante",
            notes = "Cette méthode permet de chercher et renvoyer la liste des AddressClients par ordre descroissante",
            responseContainer = "List<AddressClientDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des AddressClients par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<AddressClientDto> > getAllAddressClientsOrderByIdDesc();

    @DeleteMapping(value = APP_ROOT + "/addresseclients/delete/{idAddressClient}")
    void delete(@PathVariable("idAddressClient") Long id);
}

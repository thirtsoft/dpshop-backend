package com.dp.dpshopbackend.controller.api;

import com.dp.dpshopbackend.dto.CountryDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dp.dpshopbackend.utils.Constants.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/countries")
public interface CountryApi {

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Country",
            notes = "Cette méthode permet d'ajouter une Country", response = CountryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La Country a été crée"),
            @ApiResponse(code = 400, message = "Aucun Country  crée / modifié")
    })
    ResponseEntity<CountryDto> save(@RequestBody CountryDto countryDto);

    @PutMapping(value = "/update/{idCountry}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier une Country par son ID",
            notes = "Cette méthode permet de modifier une Country par son ID", response = CountryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Country a été modifiée"),
            @ApiResponse(code = 400, message = "La Country a n'est pas modifiée")
    })
    ResponseEntity<CountryDto> update(@PathVariable("idCountry") Long id, @RequestBody CountryDto countryDto);

    @GetMapping(value = "/findById/{idCountry}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une Country par ID",
            notes = "Cette méthode permet de chercher une Country par son ID", response = CountryDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Country a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Country n'existe avec cette ID pas dans la BD")
    })
    ResponseEntity<CountryDto> findById(@PathVariable("idCountry") Long id);

    @GetMapping(value = "/search-all-active-countries", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des countries actives",
            notes = "Cette méthode permet de chercher et renvoyer la liste des countries actives",
            responseContainer = "List<CountryDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des countries par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<CountryDto>> getAllActiveCountries();

    @DeleteMapping(value = "/delete-country/{idCountry}")
    @ApiOperation(value = "Supprimer une countrie par son ID",
            notes = "Cette méthode permet de supprimer une countries par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La countries a été supprimé")
    })
    void deleteCountry(@PathVariable("idCountry") Long idCountry);
}

package com.dp.dpshopbackend.controller.api;

import com.dp.dpshopbackend.dto.LigneCommandeDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dp.dpshopbackend.utils.Constants.APP_ROOT;

public interface LigneCommandeApi {

    @PostMapping(value = APP_ROOT + "/lignecommandes/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<LigneCommandeDto> save(@RequestBody LigneCommandeDto ligneCommandeDto);

    @GetMapping(value = APP_ROOT + "/lignecommandes/{idLignecommande}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<LigneCommandeDto> findById(@PathVariable("idLignecommande") Long id);

    @GetMapping(value = APP_ROOT + "/lignecommandes/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<LigneCommandeDto> findAll();

    @GetMapping(value = APP_ROOT + "/lignecommandes/findListArticleGroupByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    List<LigneCommandeDto> getArticlesGroupByProductIdOrderByCreatedDateDesc();

    @DeleteMapping(value = APP_ROOT + "/lignecommandes/delete/{idLignecommande}")
    void delete(@PathVariable("idLignecommande") Long id);
}

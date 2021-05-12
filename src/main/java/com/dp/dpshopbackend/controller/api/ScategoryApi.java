package com.dp.dpshopbackend.controller.api;

import com.dp.dpshopbackend.dto.CategorieDto;
import com.dp.dpshopbackend.dto.ScategorieDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dp.dpshopbackend.utils.Constants.APP_ROOT;

public interface ScategoryApi {

    @PostMapping(value = APP_ROOT + "/scategories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ScategorieDto> save(@RequestBody ScategorieDto scategorieDto);

    @GetMapping(value = APP_ROOT + "/scategories/{idScategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ScategorieDto> findById(@PathVariable("idScategory") Long id);

    @GetMapping(value = APP_ROOT + "/scategories/{libelle]", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ScategorieDto> findByLibelle(@PathVariable("libelle") String libelle);

    @GetMapping(value = APP_ROOT + "/scategories/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ScategorieDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/scategories/delete/{idScategory}")
    void delete(@PathVariable("idScategory") Long id);
}

package com.dp.dpshopbackend.controller.api;

import com.dp.dpshopbackend.dto.ScategoryDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dp.dpshopbackend.utils.Constants.APP_ROOT;

public interface ScategoryApi {

    @PostMapping(value = APP_ROOT + "/scategories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ScategoryDto> save(@RequestBody ScategoryDto scategoryDto);

    @GetMapping(value = APP_ROOT + "/scategories/{idScategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ScategoryDto> findById(@PathVariable("idScategory") Long id);

    @GetMapping(value = APP_ROOT + "/scategories/searchbyLibelle/{libelle}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ScategoryDto> findByLibelle(@PathVariable("libelle") String libelle);

    @GetMapping(value = APP_ROOT + "/scategories/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ScategoryDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/scategories/delete/{idScategory}")
    void delete(@PathVariable("idScategory") Long id);
}

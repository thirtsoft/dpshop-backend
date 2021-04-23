package com.dp.dpshopbackend.controller.api;

import com.dp.dpshopbackend.dto.CategorieDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dp.dpshopbackend.utils.Constants.APP_ROOT;

public interface CategoryApi {

    @PostMapping(value = APP_ROOT + "/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CategorieDto> save(@RequestBody CategorieDto categorieDto);

    @GetMapping(value = APP_ROOT + "/categories/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CategorieDto> findById(@PathVariable("idCategory") Long id);

    @GetMapping(value = APP_ROOT + "/categories/{designation}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CategorieDto> findByDesignation(@PathVariable("designation") String designation);

    @GetMapping(value = APP_ROOT + "/categories/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CategorieDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/categories/delete/{idCategory}")
    void delete(@PathVariable("idCategory") Long id);
}

package com.dp.dpshopbackend.controller.api;

import com.dp.dpshopbackend.dto.ArticleDto;
import com.dp.dpshopbackend.dto.RoleDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dp.dpshopbackend.utils.Constants.APP_ROOT;

public interface RoleApi {

    @PostMapping(value = APP_ROOT + "/roles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RoleDto> save(@RequestBody RoleDto roleDto);

    @GetMapping(value = APP_ROOT + "/roles/{idRole}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RoleDto> findById(@PathVariable("idRole") Long id);

    @GetMapping(value = APP_ROOT + "/roles/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<RoleDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/roles/delete/{idRole}")
    void delete(@PathVariable("idRole") Long id);
}

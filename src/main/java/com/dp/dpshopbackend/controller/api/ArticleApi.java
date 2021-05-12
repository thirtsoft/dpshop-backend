package com.dp.dpshopbackend.controller.api;

import com.dp.dpshopbackend.dto.ArticleDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dp.dpshopbackend.utils.Constants.APP_ROOT;

public interface ArticleApi {

    @PostMapping(value = APP_ROOT + "/articles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ArticleDto> save(@RequestBody ArticleDto articleDto);

    @GetMapping(value = APP_ROOT + "/articles/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ArticleDto> findById(@PathVariable("idArticle") Long id);

    @GetMapping(value = APP_ROOT + "/articles/{reference]", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ArticleDto> findByReference(@PathVariable("reference") String reference);

    @GetMapping(value = APP_ROOT + "/articles/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ArticleDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/articles/delete/{idArticle}")
    void delete(@PathVariable("idArticle") Long id);
}

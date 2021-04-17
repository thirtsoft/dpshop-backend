package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.ArticleApi;
import com.dp.dpshopbackend.dto.ProduitDto;
import com.dp.dpshopbackend.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController implements ArticleApi {

    private ProduitService articleService;

    @Autowired
    public ArticleController(ProduitService articleService) {
        this.articleService = articleService;
    }

    @Override
    public ResponseEntity<ProduitDto> save(ProduitDto produitDto) {
        return ResponseEntity.ok(articleService.save(produitDto));
    }

    @Override
    public ResponseEntity<ProduitDto> findById(Long id) {
        return ResponseEntity.ok(articleService.findById(id));
    }

    @Override
    public ResponseEntity<ProduitDto> findByReference(String reference) {
        return ResponseEntity.ok(articleService.findByReference(reference));
    }

    @Override
    public List<ProduitDto> findAll() {
        return articleService.findAll();
    }

    @Override
    public void delete(Long id) {
        articleService.delete(id);
    }


}

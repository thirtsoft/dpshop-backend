package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.CategoryApi;
import com.dp.dpshopbackend.dto.CategorieDto;
import com.dp.dpshopbackend.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class CategoryController implements CategoryApi {

    private CategorieService categorieService;

    @Autowired
    public CategoryController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @Override
    public ResponseEntity<CategorieDto> save(CategorieDto categorieDto) {
        return ResponseEntity.ok(categorieService.save(categorieDto));
    }

    @Override
    public ResponseEntity<CategorieDto> findById(Long id) {
        return ResponseEntity.ok(categorieService.findById(id));
    }

    @Override
    public ResponseEntity<CategorieDto> findByDesignation(String designation) {
        return ResponseEntity.ok(categorieService.findByDesignation(designation));
    }

    @Override
    public List<CategorieDto> findAll() {
        return categorieService.findAll();
    }

    @Override
    public void delete(Long id) {
        categorieService.delete(id);
    }
}

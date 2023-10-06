package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.ScategoryApi;
import com.dp.dpshopbackend.dto.ScategoryDto;
import com.dp.dpshopbackend.services.ScategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScategoryController implements ScategoryApi {

    private final ScategoryService scategoryService;

    @Autowired
    public ScategoryController(ScategoryService scategoryService) {
        this.scategoryService = scategoryService;
    }

    @Override
    public ResponseEntity<ScategoryDto> save(ScategoryDto scategoryDto) {
        return ResponseEntity.ok(scategoryService.save(scategoryDto));
    }

    @Override
    public ResponseEntity<ScategoryDto> update(Long id, ScategoryDto scategoryDto) {
        scategoryDto.setId(id);
        return ResponseEntity.ok(scategoryService.save(scategoryDto));
    }

    @Override
    public ResponseEntity<ScategoryDto> findById(Long id) {
        return ResponseEntity.ok(scategoryService.findById(id));
    }

    @Override
    public ResponseEntity<List<ScategoryDto>> getAllActiveSubCategories() {
        List<ScategoryDto> scategoryDtoList = scategoryService.findAllActiveSubcategories();
        return new ResponseEntity<>(scategoryDtoList, HttpStatus.OK);
    }

    @Override
    public void deleteSubCategory(Long idScategory) {
        scategoryService.deleteSubcategory(idScategory);
    }

}

package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.ScategoryApi;
import com.dp.dpshopbackend.dto.ScategorieDto;
import com.dp.dpshopbackend.services.ScategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class ScategoryController implements ScategoryApi {

    private ScategorieService scategorieService;

    @Autowired
    public ScategoryController(ScategorieService scategorieService) {
        this.scategorieService = scategorieService;
    }

    @Override
    public ResponseEntity<ScategorieDto> save(ScategorieDto scategorieDto) {
        return ResponseEntity.ok(scategorieService.save(scategorieDto));
    }

    @Override
    public ResponseEntity<ScategorieDto> findById(Long id) {
        return ResponseEntity.ok(scategorieService.findById(id));
    }

    @Override
    public ResponseEntity<ScategorieDto> findByLibelle(String libelle) {
        return ResponseEntity.ok(scategorieService.findByLibelle(libelle));
    }

    @Override
    public List<ScategorieDto> findAll() {
        return scategorieService.findAll();
    }

    @Override
    public void delete(Long id) {
        scategorieService.delete(id);
    }

}

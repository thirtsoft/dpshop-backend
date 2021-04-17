package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.LigneCommandeApi;
import com.dp.dpshopbackend.dto.LigneCommandeDto;
import com.dp.dpshopbackend.services.LigneCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LigneCommandeController implements LigneCommandeApi {

    private LigneCommandeService ligneCommandeService;

    @Autowired
    public LigneCommandeController(LigneCommandeService ligneCommandeService) {
        this.ligneCommandeService = ligneCommandeService;
    }

    @Override
    public ResponseEntity<LigneCommandeDto> save(LigneCommandeDto ligneCommandeDto) {
        return ResponseEntity.ok(ligneCommandeService.save(ligneCommandeDto));
    }

    @Override
    public ResponseEntity<LigneCommandeDto> findById(Long id) {
        return ResponseEntity.ok(ligneCommandeService.findById(id));
    }

    @Override
    public List<LigneCommandeDto> findAll() {
        return ligneCommandeService.findAll();
    }

    @Override
    public void delete(Long id) {
        ligneCommandeService.delete(id);
    }
}

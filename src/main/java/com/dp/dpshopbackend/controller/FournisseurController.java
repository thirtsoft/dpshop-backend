package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.FournisseurApi;
import com.dp.dpshopbackend.dto.FournisseurDto;
import com.dp.dpshopbackend.services.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class FournisseurController implements FournisseurApi {

    private FournisseurService fournisseurService;

    @Autowired
    public FournisseurController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @Override
    public ResponseEntity<FournisseurDto> save(FournisseurDto fournisseurDto) {
        return ResponseEntity.ok(fournisseurService.save(fournisseurDto));
    }

    @Override
    public ResponseEntity<FournisseurDto> update(Long id, FournisseurDto fournisseurDto) {
        fournisseurDto.setId(id);
        return ResponseEntity.ok(fournisseurService.save(fournisseurDto));
    }

    @Override
    public ResponseEntity<FournisseurDto> findById(Long id) {
        return ResponseEntity.ok(fournisseurService.findById(id));
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurService.findAll();
    }

    @Override
    public void delete(Long id) {
        fournisseurService.delete(id);
    }
}

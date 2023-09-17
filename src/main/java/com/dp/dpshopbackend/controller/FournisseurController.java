package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.FournisseurApi;
import com.dp.dpshopbackend.dto.FournisseurDto;
import com.dp.dpshopbackend.services.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class FournisseurController implements FournisseurApi {

    private final FournisseurService fournisseurService;

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
    public ResponseEntity<List<FournisseurDto>> getAllFournisseursOrderByIdDesc() {
        List<FournisseurDto> fournisseurDtoList = fournisseurService.findByOrderByIdDesc();
        return new ResponseEntity<>(fournisseurDtoList, HttpStatus.OK);
    }

    @Override
    public BigDecimal countNumberOfFournisseurs() {
        return fournisseurService.countNumberOfFournisseur();
    }

    @Override
    public void delete(Long id) {
        fournisseurService.delete(id);
    }

    @Override
    public ResponseEntity<List<FournisseurDto>> getAllActiveFournisseurs() {
        List<FournisseurDto> fournisseurDtoList = fournisseurService.findAllActiveFournisseurs();
        return new ResponseEntity<>(fournisseurDtoList, HttpStatus.OK);
    }

    @Override
    public void deleteFournisseur(Long idFournisseur) {
        fournisseurService.deleteFournisseur(idFournisseur);
    }
}

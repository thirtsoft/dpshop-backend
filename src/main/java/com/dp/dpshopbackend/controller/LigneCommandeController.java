package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.LigneCommandeApi;
import com.dp.dpshopbackend.dto.HistoriqueLoginDto;
import com.dp.dpshopbackend.dto.LigneCommandeDto;
import com.dp.dpshopbackend.services.LigneCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LigneCommandeController implements LigneCommandeApi {

    private final LigneCommandeService ligneCommandeService;

    @Autowired
    public LigneCommandeController(LigneCommandeService ligneCommandeService) {
        this.ligneCommandeService = ligneCommandeService;
    }

    @Override
    public ResponseEntity<LigneCommandeDto> findById(Long id) {
        return ResponseEntity.ok(ligneCommandeService.findById(id));
    }

    @Override
    public List<LigneCommandeDto> getArticlesGroupByProductIdOrderByCreatedDateDesc() {
        return ligneCommandeService.findArticlesGroupByProductIdOrderByCreatedDateDesc();
    }

    @Override
    public ResponseEntity<List<LigneCommandeDto>> getAllLigneCommandesByCommandeId(Long comId) {
        List<LigneCommandeDto> ligneCommandeDtoList = ligneCommandeService.findListLigneCommandeByCommandeId(comId);
        return new ResponseEntity<>(ligneCommandeDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LigneCommandeDto>> getTop200LigneCommandesOrderByIdDesc() {
        List<LigneCommandeDto> ligneCommandeDtoList = ligneCommandeService.findTop200LigneCommandeOrderByIdDesc();
        return new ResponseEntity<>(ligneCommandeDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LigneCommandeDto>> getAllActiveLigneCommandes() {
        List<LigneCommandeDto> ligneCommandeDtoList = ligneCommandeService.findAllActiveLigneCommandes();
        return new ResponseEntity<>(ligneCommandeDtoList, HttpStatus.OK);
    }

}

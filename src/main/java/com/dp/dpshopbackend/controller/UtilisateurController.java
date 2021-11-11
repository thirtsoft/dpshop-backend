package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.UtilisateurApi;
import com.dp.dpshopbackend.dto.UtilisateurDto;
import com.dp.dpshopbackend.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class UtilisateurController implements UtilisateurApi {

    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public ResponseEntity<UtilisateurDto> save(UtilisateurDto utilisateurDto) {
        return ResponseEntity.ok(utilisateurService.save(utilisateurDto));
    }

    @Override
    public ResponseEntity<UtilisateurDto> findById(Long id) {
        return ResponseEntity.ok(utilisateurService.findById(id));
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurService.findAll();
    }

    @Override
    public ResponseEntity<List<UtilisateurDto>> getAllUtilisateursOrderByIdDesc() {
        List<UtilisateurDto> utilisateurDtoList = utilisateurService.findByOrderByIdDesc();
        return new ResponseEntity<>(utilisateurDtoList, HttpStatus.OK);
    }

    @Override
    public void delete(Long id) {
        utilisateurService.delete(id);
    }
}

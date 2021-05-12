package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.LigneCommandeDto;

import java.util.List;

public interface LigneCommandeService {

    LigneCommandeDto save(LigneCommandeDto ligneCommandeDto);

    LigneCommandeDto findById(Long id);

    //  ScategorieDto findByLibelle(String libelle);

    List<LigneCommandeDto> findAll();

    void delete(Long id);
}

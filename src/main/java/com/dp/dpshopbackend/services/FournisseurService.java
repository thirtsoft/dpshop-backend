package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.FournisseurDto;

import java.util.List;

public interface FournisseurService {

    FournisseurDto save(FournisseurDto fournisseurDto);

    FournisseurDto update(Long id, FournisseurDto fournisseurDto);

    FournisseurDto findById(Long id);

    //  ScategorieDto findByLibelle(String libelle);

    List<FournisseurDto> findAll();

    void delete(Long id);
}

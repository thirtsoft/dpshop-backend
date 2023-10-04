package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.FournisseurDto;

import java.math.BigDecimal;
import java.util.List;

public interface FournisseurService {

    FournisseurDto save(FournisseurDto fournisseurDto);

    FournisseurDto update(Long id, FournisseurDto fournisseurDto);

    FournisseurDto findById(Long id);

    BigDecimal countNumberOfFournisseur();

    List<FournisseurDto> findAllActiveFournisseurs();

    void deleteFournisseur(Long fournisseurId);

}

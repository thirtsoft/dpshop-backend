package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.HistoriqueLoginDto;
import com.dp.dpshopbackend.dto.LigneCommandeDto;

import java.util.List;

public interface LigneCommandeService {

    LigneCommandeDto save(LigneCommandeDto ligneCommandeDto);

    LigneCommandeDto findById(Long id);

    List<LigneCommandeDto> findAll();

    List<LigneCommandeDto> findByOrderByIdDesc();

    List<LigneCommandeDto> findArticlesGroupByProductIdOrderByCreatedDateDesc();

    List<LigneCommandeDto> findListLigneCommandeByCommandeId(Long comId);

    List<LigneCommandeDto> findTop200LigneCommandeOrderByIdDesc();

    void delete(Long id);

    List<LigneCommandeDto> findAllActiveLigneCommandes();

    void deleteLigneCommande(Long ligneCommandeId);
}

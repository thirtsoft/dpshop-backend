package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.LigneCommandeDto;

import java.util.List;

public interface LigneCommandeService {

    LigneCommandeDto findById(Long id);

    List<LigneCommandeDto> findArticlesGroupByProductIdOrderByCreatedDateDesc();

    List<LigneCommandeDto> findListLigneCommandeByCommandeId(Long comId);

    List<LigneCommandeDto> findTop200LigneCommandeOrderByIdDesc();

    List<LigneCommandeDto> findAllActiveLigneCommandes();

}

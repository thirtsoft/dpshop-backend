package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.LigneCommandeDto;

import java.util.List;

public interface LigneCommandeService {

    LigneCommandeDto save(LigneCommandeDto ligneCommandeDto);

    LigneCommandeDto findById(Long id);

    List<LigneCommandeDto> findAll();

    List<LigneCommandeDto> findArticlesGroupByProductIdOrderByCreatedDateDesc();

    void delete(Long id);
}

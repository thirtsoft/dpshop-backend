package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.ScategorieDto;

import java.util.List;

public interface ScategorieService {

    ScategorieDto save(ScategorieDto scategorieDto);

    ScategorieDto findById(Long id);

    ScategorieDto findByLibelle(String libelle);

    List<ScategorieDto> findAll();

    void delete(Long id);
}
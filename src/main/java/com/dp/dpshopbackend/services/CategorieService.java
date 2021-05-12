package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.CategorieDto;

import java.util.List;

public interface CategorieService {

    CategorieDto save(CategorieDto categorieDto);

    CategorieDto findById(Long id);

    CategorieDto findByDesignation(String designation);

    List<CategorieDto> findAll();

    void delete(Long id);

}

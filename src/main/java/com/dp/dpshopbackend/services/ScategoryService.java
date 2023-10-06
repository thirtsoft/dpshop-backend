package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.ScategoryDto;

import java.util.List;

public interface ScategoryService {

    ScategoryDto save(ScategoryDto scategoryDto);

    ScategoryDto update(Long id, ScategoryDto scategoryDto);

    ScategoryDto findById(Long id);

    List<ScategoryDto> findAllActiveSubcategories();

    void deleteSubcategory(Long subcategoryId);
}
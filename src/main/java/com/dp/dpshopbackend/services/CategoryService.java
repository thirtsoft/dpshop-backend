package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.AddressLivraisonDto;
import com.dp.dpshopbackend.dto.ArticleDto;
import com.dp.dpshopbackend.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto save(CategoryDto categoryDto);

    CategoryDto update(Long id, CategoryDto categoryDto);

    CategoryDto findById(Long id);

    CategoryDto findByDesignation(String designation);

    List<CategoryDto> findAll();

    List<CategoryDto> findByOrderByIdDesc();

    void delete(Long id);

    List<CategoryDto> findAllActiveCategories();

    void deleteCategory(Long categoryId);

}

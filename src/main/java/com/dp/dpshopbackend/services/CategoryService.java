package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto save(CategoryDto categoryDto);

    CategoryDto update(Long id, CategoryDto categoryDto);

    CategoryDto findById(Long id);

    List<CategoryDto> findAllActiveCategories();

    void deleteCategory(Long categoryId);

}

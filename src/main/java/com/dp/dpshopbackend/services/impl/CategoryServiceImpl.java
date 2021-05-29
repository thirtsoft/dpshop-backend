package com.dp.dpshopbackend.services.impl;

import com.dp.dpshopbackend.dto.CategoryDto;
import com.dp.dpshopbackend.exceptions.ResourceNotFoundException;
import com.dp.dpshopbackend.models.Category;
import com.dp.dpshopbackend.repository.CategoryRepository;
import com.dp.dpshopbackend.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {

        return CategoryDto.fromEntityToDto(
                categoryRepository.save(
                        CategoryDto.fromDtoToEntity(categoryDto)
                )
        );
    }

    @Override
    public CategoryDto update(Long id, CategoryDto categoryDto) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found");
        }

        Optional<Category> category = categoryRepository.findById(id);

        if (!category.isPresent()) {
            throw new ResourceNotFoundException("Category not found");
        }

        CategoryDto categoryResult = CategoryDto.fromEntityToDto(category.get());

        categoryResult.setCode(categoryDto.getCode());
        categoryResult.setDesignation(categoryDto.getDesignation());

        return CategoryDto.fromEntityToDto(
                categoryRepository.save(
                        CategoryDto.fromDtoToEntity(categoryResult)
                )
        );
    }

    @Override
    public CategoryDto findById(Long id) {
        if (id == null) {
            log.error("Category Id is null");
            return null;
        }

        Optional<Category> categorie = categoryRepository.findById(id);

        return Optional.of(CategoryDto.fromEntityToDto(categorie.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Category avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public CategoryDto findByDesignation(String designation) {
        if (!StringUtils.hasLength(designation)) {
            log.error("Category REFERENCE is null");
        }

        Optional<Category> categorie = categoryRepository.findCategorieByDesignation(designation);

        return Optional.of(CategoryDto.fromEntityToDto(categorie.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Category avec l'Id = " + designation + "n'a été trouvé")
        );

    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Categorie Id is null");
            return;
        }
        categoryRepository.deleteById(id);

    }
}

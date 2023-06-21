package com.dp.dpshopbackend.dto;

import com.dp.dpshopbackend.models.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private Long id;

    private String code;

    @NotNull(message = "La désignation de la catégory ne doit pas etre vide")
    @NotEmpty(message = "La désignation de la catégory ne doit pas etre vide")
    @NotBlank(message = "La désignation de la catégory ne doit pas etre vide")
    private String designation;

    private int actif;

    public void setActif(boolean actif) {
        if (actif == true)
            this.actif = 1;
        else
            this.actif = 0;
    }

    public boolean isActif() {
        if (actif == 1)
            return true;
        else
            return false;
    }

    public static CategoryDto fromEntityToDto(Category category) {
        if (category == null) {
            return null;
        }

        return CategoryDto.builder()
                .id(category.getId())
                .code(category.getCode())
                .designation(category.getDesignation())
                .actif(category.getActif())
                .build();
    }

    public static Category fromDtoToEntity(CategoryDto categoryDto) {
        if (categoryDto == null) {
            return null;
        }

        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setCode(categoryDto.getCode());
        category.setDesignation(categoryDto.getDesignation());
        category.setActif(categoryDto.isActif());
        return category;
    }

}

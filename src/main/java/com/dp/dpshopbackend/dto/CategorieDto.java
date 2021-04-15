package com.dp.dpshopbackend.dto;

import com.dp.dpshopbackend.models.Categorie;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategorieDto {

    private long id;

    private String code;

    private String designation;

    public static CategorieDto fromEntityToDto(Categorie categorie) {
        if (categorie == null) {
            return null;
        }

        return CategorieDto.builder()
                .id(categorie.getId())
                .code(categorie.getCode())
                .designation(categorie.getDesignation())
                .build();
    }

    public static Categorie fromDtoToEntity(CategorieDto categorieDto) {
        if (categorieDto == null) {
            return null;
        }

        Categorie categorie = new Categorie();
        categorie.setId(categorieDto.getId());
        categorie.setCode(categorieDto.getCode());
        categorie.setDesignation(categorieDto.getDesignation());

        return categorie;
    }

}

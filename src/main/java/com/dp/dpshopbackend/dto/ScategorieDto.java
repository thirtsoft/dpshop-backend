package com.dp.dpshopbackend.dto;

import com.dp.dpshopbackend.models.Scategorie;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScategorieDto {

    private String code;

    private String libelle;

    private CategorieDto categorieDto;

    public static ScategorieDto fromEntityToDto(Scategorie scategorie) {
        if (scategorie == null) {
            return null;
        }

        return ScategorieDto.builder()
                .code(scategorie.getCode())
                .libelle(scategorie.getLibelle())
                .categorieDto(CategorieDto.fromEntityToDto(scategorie.getCategorie()))
                .build();
    }

    public static Scategorie fromDtoToEntity(ScategorieDto scategorieDto) {
        if (scategorieDto == null) {
            return null;
        }

        Scategorie scategorie = new Scategorie();
        scategorie.setCode(scategorieDto.getCode());
        scategorie.setLibelle(scategorieDto.getLibelle());

        return scategorie;
    }
}

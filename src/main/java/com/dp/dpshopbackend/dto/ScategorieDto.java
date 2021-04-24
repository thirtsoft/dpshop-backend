package com.dp.dpshopbackend.dto;

import com.dp.dpshopbackend.models.Scategorie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScategorieDto {

    private long id;

    private String code;

    private String libelle;

    private CategorieDto categorieDto;

    public static ScategorieDto fromEntityToDto(Scategorie scategorie) {
        if (scategorie == null) {
            return null;
        }

        return ScategorieDto.builder()
                .id(scategorie.getId())
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
        scategorie.setId(scategorieDto.getId());
        scategorie.setCode(scategorieDto.getCode());
        scategorie.setLibelle(scategorieDto.getLibelle());
        scategorie.setCategorie(CategorieDto.fromDtoToEntity(scategorieDto.getCategorieDto()));

        return scategorie;
    }
}

package com.dp.dpshopbackend.dto;

import com.dp.dpshopbackend.models.Scategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScategoryDto {

    private Long id;

    private String code;

    private String libelle;

    private CategoryDto categoryDto;

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

    public static ScategoryDto fromEntityToDto(Scategory scategory) {
        if (scategory == null) {
            return null;
        }

        return ScategoryDto.builder()
                .id(scategory.getId())
                .code(scategory.getCode())
                .libelle(scategory.getLibelle())
                .categoryDto(CategoryDto.fromEntityToDto(scategory.getCategory()))
                .actif(scategory.getActif())
                .build();
    }

    public static Scategory fromDtoToEntity(ScategoryDto scategoryDto) {
        if (scategoryDto == null) {
            return null;
        }

        Scategory scategory = new Scategory();
        scategory.setId(scategoryDto.getId());
        scategory.setCode(scategoryDto.getCode());
        scategory.setLibelle(scategoryDto.getLibelle());
        scategory.setCategory(CategoryDto.fromDtoToEntity(scategoryDto.getCategoryDto()));
        scategory.setActif(scategoryDto.isActif());
        return scategory;
    }
}

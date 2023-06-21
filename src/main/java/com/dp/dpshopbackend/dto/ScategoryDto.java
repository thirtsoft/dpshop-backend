package com.dp.dpshopbackend.dto;

import com.dp.dpshopbackend.models.Scategory;
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
public class ScategoryDto {

    private Long id;

    private String code;

    @NotNull(message = "Le libelle ne doit pas etre vide")
    @NotEmpty(message = "Le libelle ne doit pas etre vide")
    @NotBlank(message = "Le libelle ne doit pas etre vide")
    private String libelle;

    @NotNull(message = "La category ne doit pas etre vide")
    @NotEmpty(message = "La category ne doit pas etre vide")
    @NotBlank(message = "La category ne doit pas etre vide")
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

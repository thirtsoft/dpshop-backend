package com.dp.dpshopbackend.dto;


import com.dp.dpshopbackend.models.State;
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
public class StateDto {

    private Long id;

    @NotNull(message = "Le nom ne doit pas etre vide")
    @NotEmpty(message = "Le nom ne doit pas etre vide")
    @NotBlank(message = "Le nom ne doit pas etre vide")
    private String name;

    @NotNull(message = "La region ne doit pas etre vide")
    @NotEmpty(message = "La region ne doit pas etre vide")
    @NotBlank(message = "La region ne doit pas etre vide")
    private CountryDto countryDto;

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

    public static StateDto fromEntityToDto(State state) {
        if (state == null) {
            return null;
        }

        return StateDto.builder()
                .id(state.getId())
                .name(state.getName())
                .countryDto(CountryDto.fromEntityToDto(state.getCountry()))
                .actif(state.getActif())
                .build();
    }

    public static State fromDtoToEntity(StateDto stateDto) {
        if (stateDto == null) {
            return null;
        }

        State state = new State();
        state.setId(stateDto.getId());
        state.setName(stateDto.getName());
        state.setCountry(CountryDto.fromDtoToEntity(stateDto.getCountryDto()));
        state.setActif(stateDto.isActif());
        return state;
    }
}

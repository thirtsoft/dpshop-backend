package com.dp.dpshopbackend.dto;


import com.dp.dpshopbackend.models.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StateDto {

    private Long idState;

    private String name;

    private CountryDto countryDto;

    public static StateDto fromEntityToDto(State state) {
        if (state == null) {
            return null;
        }

        return StateDto.builder()
                .idState(state.getIdState())
                .name(state.getName())
                .countryDto(CountryDto.fromEntityToDto(state.getCountry()))
                .build();
    }

    public static State fromDtoToEntity(StateDto stateDto) {
        if (stateDto == null) {
            return null;
        }

        State state = new State();
        state.setIdState(stateDto.getIdState());
        state.setName(stateDto.getName());
        state.setCountry(CountryDto.fromDtoToEntity(stateDto.getCountryDto()));

        return state;
    }
}

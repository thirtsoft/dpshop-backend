package com.dp.dpshopbackend.dto;


import com.dp.dpshopbackend.models.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryDto {

    private Long idCountry;

    private String name;

    public static CountryDto fromEntityToDto(Country country) {
        if (country == null) {
            return null;
        }

        return CountryDto.builder()
                .idCountry(country.getIdCountry())
                .name(country.getName())
                .build();
    }

    public static Country fromDtoToEntity(CountryDto countryDto) {
        if (countryDto == null) {
            return null;
        }

        Country country = new Country();
        country.setIdCountry(countryDto.getIdCountry());
        country.setName(countryDto.getName());

        return country;
    }

}

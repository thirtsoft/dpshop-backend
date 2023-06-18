package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.CommandeDto;
import com.dp.dpshopbackend.dto.CountryDto;

import java.util.List;

public interface CountryService {

    CountryDto save(CountryDto countryDto);

    CountryDto update(Long id, CountryDto countryDto);

    CountryDto findById(Long id);

    List<CountryDto> findAll();

    List<CountryDto> findByOrderByIdDesc();

    void delete(Long id);

    List<CountryDto> findAllActiveCountries();

    void deleteCountry(Long countryId);
}

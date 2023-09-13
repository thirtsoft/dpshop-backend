package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.CountryApi;
import com.dp.dpshopbackend.dto.CountryDto;
import com.dp.dpshopbackend.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {"https://soulbusinesse.com"})
@RestController
public class CountryController implements CountryApi {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }


    @Override
    public ResponseEntity<CountryDto> save(CountryDto countryDto) {
        return ResponseEntity.ok(countryService.save(countryDto));
    }

    @Override
    public ResponseEntity<CountryDto> update(Long id, CountryDto countryDto) {
        countryDto.setId(id);
        return ResponseEntity.ok(countryService.save(countryDto));
    }

    @Override
    public ResponseEntity<CountryDto> findById(Long id) {
        return ResponseEntity.ok(countryService.findById(id));
    }

    @Override
    public List<CountryDto> findAll() {
        return countryService.findAll();
    }

    @Override
    public ResponseEntity<List<CountryDto>> getAllCountryOrderByIdDesc() {
        List<CountryDto> countryDtoList = countryService.findByOrderByIdDesc();
        return new ResponseEntity<>(countryDtoList, HttpStatus.OK);
    }

    @Override
    public void delete(Long id) {
        countryService.delete(id);
    }

    @Override
    public ResponseEntity<List<CountryDto>> getAllActiveCountries() {
        List<CountryDto> countryDtoList = countryService.findAllActiveCountries();
        return new ResponseEntity<>(countryDtoList, HttpStatus.OK);
    }

    @Override
    public void deleteCountry(Long idCountry) {
        countryService.deleteCountry(idCountry);
    }
}

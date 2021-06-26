package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.CountryApi;
import com.dp.dpshopbackend.dto.CountryDto;
import com.dp.dpshopbackend.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
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
        countryDto.setIdCountry(id);
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
    public void delete(Long id) {
        countryService.delete(id);
    }
}

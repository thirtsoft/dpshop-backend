package com.dp.dpshopbackend.services.impl;

import com.dp.dpshopbackend.dto.CountryDto;
import com.dp.dpshopbackend.exceptions.ResourceNotFoundException;
import com.dp.dpshopbackend.models.Country;
import com.dp.dpshopbackend.repository.CountryRepository;
import com.dp.dpshopbackend.services.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class CountryServiceImpl implements CountryService {

    @Autowired
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    @Override
    public CountryDto save(CountryDto countryDto) {
        countryDto.setActif(true);
        return CountryDto.fromEntityToDto(
                countryRepository.save(
                        CountryDto.fromDtoToEntity(countryDto)
                )
        );
    }

    @Override
    public CountryDto update(Long id, CountryDto countryDto) {
        if (!countryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Country not found");
        }

        Optional<Country> countryOptional = countryRepository.findById(id);

        if (!countryOptional.isPresent()) {
            throw new ResourceNotFoundException("Country not found");
        }

        CountryDto countryResult = CountryDto.fromEntityToDto(countryOptional.get());

        countryResult.setName(countryDto.getName());

        return CountryDto.fromEntityToDto(
                countryRepository.save(
                        CountryDto.fromDtoToEntity(countryResult)
                )
        );
    }

    @Override
    public CountryDto findById(Long id) {
        if (id == null) {
            log.error("Country Id is null");
            return null;
        }

        Optional<Country> countryOptional = countryRepository.findById(id);

        return Optional.of(CountryDto.fromEntityToDto(countryOptional.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Country avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<CountryDto> findAll() {
        return countryRepository.findAll().stream()
                .map(CountryDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CountryDto> findByOrderByIdDesc() {
        return countryRepository.findByOrderByIdDesc().stream()
                .map(CountryDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Country Id is null");
        }
        countryRepository.deleteById(id);

    }

    @Override
    public List<CountryDto> findAllActiveCountries() {
        return countryRepository.findAll().stream()
                .map(CountryDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCountry(Long countryId) {
        if (countryId == null) {
            log.error("Country Id is null");
            return;
        }
        Optional<Country> countryOptional = countryRepository.findById(countryId);
        Country country = countryOptional.get();
        country.setActif(false);
        countryRepository.save(country);
    }
}

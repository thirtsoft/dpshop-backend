package com.dp.dpshopbackend.services.impl;

import com.dp.dpshopbackend.dto.CategorieDto;
import com.dp.dpshopbackend.exceptions.ResourceNotFoundException;
import com.dp.dpshopbackend.models.Categorie;
import com.dp.dpshopbackend.repository.CategorieRepository;
import com.dp.dpshopbackend.services.CategorieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class CategorieServiceImpl implements CategorieService {


    @Autowired
    private final CategorieRepository categorieRepository;

    public CategorieServiceImpl(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    @Override
    public CategorieDto save(CategorieDto categorieDto) {

        return CategorieDto.fromEntityToDto(
                categorieRepository.save(
                        CategorieDto.fromDtoToEntity(categorieDto)
                )
        );
    }

    @Override
    public CategorieDto findById(Long id) {
        if (id == null) {
            log.error("Produit Id is null");
            return null;
        }

        Optional<Categorie> categorie = categorieRepository.findById(id);

        return Optional.of(CategorieDto.fromEntityToDto(categorie.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun categorie avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public CategorieDto findByDesignation(String designation) {
        if (!StringUtils.hasLength(designation)) {
            log.error("Produit REFERENCE is null");
        }

        Optional<Categorie> categorie = categorieRepository.findCategorieByDesignation(designation);

        return Optional.of(CategorieDto.fromEntityToDto(categorie.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun categorie avec l'Id = " + designation + "n'a été trouvé")
        );


    }

    @Override
    public List<CategorieDto> findAll() {
        return categorieRepository.findAll().stream()
                .map(CategorieDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Categorie Id is null");
            return;
        }
        categorieRepository.deleteById(id);

    }
}

package com.dp.dpshopbackend.services.impl;

import com.dp.dpshopbackend.dto.ProduitDto;
import com.dp.dpshopbackend.exceptions.ResourceNotFoundException;
import com.dp.dpshopbackend.models.Produit;
import com.dp.dpshopbackend.repository.ProduitRepository;
import com.dp.dpshopbackend.services.ProduitService;
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
public class ProduitServiceImpl implements ProduitService {

    @Autowired
    private final ProduitRepository produitRepository;

    public ProduitServiceImpl(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @Override
    public ProduitDto save(ProduitDto produitDto) {

        return ProduitDto.fromEntityToDto(
                produitRepository.save(
                        ProduitDto.fromDtoToEntity(produitDto)
                )
        );
    }

    @Override
    public ProduitDto findById(Long id) {
        if (id == null) {
            log.error("Produit Id is null");
            return null;
        }

        Optional<Produit> produit = produitRepository.findById(id);

        return Optional.of(ProduitDto.fromEntityToDto(produit.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun produit avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public ProduitDto findByReference(String reference) {
        if (!StringUtils.hasLength(reference)) {
            log.error("Produit REFERENCE is null");
        }

        Optional<Produit> produit = produitRepository.findProduitByReference(reference);

        return Optional.of(ProduitDto.fromEntityToDto(produit.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun produit avec l'Id = " + reference + "n'a été trouvé")
        );


    }

    @Override
    public List<ProduitDto> findAll() {
        return produitRepository.findAll().stream()
                .map(ProduitDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Produit Id is null");
            return;
        }
        produitRepository.deleteById(id);

    }
}

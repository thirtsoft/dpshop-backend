package com.dp.dpshopbackend.services.impl;

import com.dp.dpshopbackend.dto.LigneCommandeDto;
import com.dp.dpshopbackend.exceptions.ResourceNotFoundException;
import com.dp.dpshopbackend.models.LigneCommande;
import com.dp.dpshopbackend.repository.LigneCommandeRepository;
import com.dp.dpshopbackend.services.LigneCommandeService;
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
public class LigneCommandeServiceImpl implements LigneCommandeService {

    private final LigneCommandeRepository ligneCommandeRepository;

    public LigneCommandeServiceImpl(LigneCommandeRepository ligneCommandeRepository) {
        this.ligneCommandeRepository = ligneCommandeRepository;
    }

    @Override
    public LigneCommandeDto findById(Long id) {
        if (id == null) {
            log.error("LigneCommande Id is null");
            return null;
        }
        Optional<LigneCommande> ligneCommande = ligneCommandeRepository.findById(id);
        return Optional.of(LigneCommandeDto.fromEntityToDto(ligneCommande.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun LigneCommande avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<LigneCommandeDto> findArticlesGroupByProductIdOrderByCreatedDateDesc() {
        return ligneCommandeRepository.findArticlesGroupByProductId().stream()
                .map(LigneCommandeDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LigneCommandeDto> findListLigneCommandeByCommandeId(Long comId) {
        return ligneCommandeRepository.ListLigneCommandeByCommandeId(comId).stream()
                .map(LigneCommandeDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LigneCommandeDto> findTop200LigneCommandeOrderByIdDesc() {
        return ligneCommandeRepository.findTop200ByOrderByIdDesc().stream()
                .map(LigneCommandeDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LigneCommandeDto> findAllActiveLigneCommandes() {
        return ligneCommandeRepository.findAll().stream()
                .map(LigneCommandeDto::fromEntityToDto)
                .collect(Collectors.toList());
    }
}

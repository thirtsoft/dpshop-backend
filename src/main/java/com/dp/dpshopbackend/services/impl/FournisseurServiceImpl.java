package com.dp.dpshopbackend.services.impl;

import com.dp.dpshopbackend.dto.FournisseurDto;
import com.dp.dpshopbackend.exceptions.ResourceNotFoundException;
import com.dp.dpshopbackend.models.Fournisseur;
import com.dp.dpshopbackend.repository.FournisseurRepository;
import com.dp.dpshopbackend.services.FournisseurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {

    @Autowired
    private final FournisseurRepository fournisseurRepository;

    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
    }

    @Override
    public FournisseurDto save(FournisseurDto fournisseurDto) {
        fournisseurDto.setActif(true);
        return FournisseurDto.fromEntityToDto(
                fournisseurRepository.save(
                        FournisseurDto.fromDtoToEntity(fournisseurDto)
                )
        );
    }

    @Override
    public FournisseurDto update(Long id, FournisseurDto fournisseurDto) {
        if (!fournisseurRepository.existsById(id)) {
            throw new ResourceNotFoundException("Fournisseur not found");
        }
        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(id);
        if (!fournisseur.isPresent()) {
            throw new ResourceNotFoundException("Fournisseur not found");
        }
        FournisseurDto fournisseurDTOResult = FournisseurDto.fromEntityToDto(fournisseur.get());
        fournisseurDTOResult.setReference(fournisseurDto.getReference());
        fournisseurDTOResult.setFirstName(fournisseurDto.getFirstName());
        fournisseurDTOResult.setLastName(fournisseurDto.getLastName());
        fournisseurDTOResult.setAddress(fournisseurDto.getAddress());
        fournisseurDTOResult.setTelephoneFournisseur(fournisseurDto.getTelephoneFournisseur());
        fournisseurDTOResult.setEmail(fournisseurDto.getEmail());
        fournisseurDTOResult.setCity(fournisseurDto.getCity());
        fournisseurDTOResult.setCountry(fournisseurDto.getCountry());
        return FournisseurDto.fromEntityToDto(
                fournisseurRepository.save(
                        FournisseurDto.fromDtoToEntity(fournisseurDTOResult)
                )
        );
    }

    @Override
    public FournisseurDto findById(Long id) {
        if (id == null) {
            log.error("Fournisseur Id is null");
            return null;
        }
        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(id);
        return Optional.of(FournisseurDto.fromEntityToDto(fournisseur.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Fournisseur avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public BigDecimal countNumberOfFournisseur() {
        return fournisseurRepository.countNumberOfFournisseur();
    }

    @Override
    public List<FournisseurDto> findAllActiveFournisseurs() {
        return fournisseurRepository.findAll().stream()
                .map(FournisseurDto::fromEntityToDto)
                .collect(Collectors.toList()
                );
    }

    @Override
    public void deleteFournisseur(Long fournisseurId) {
        if (fournisseurId == null) {
            log.error("Fournisseur Id is null");
            return;
        }
        Optional<Fournisseur> optionalFournisseur = fournisseurRepository.findById(fournisseurId);
        Fournisseur fournisseur = optionalFournisseur.get();
        fournisseur.setActif(false);
        fournisseurRepository.save(fournisseur);
    }
}
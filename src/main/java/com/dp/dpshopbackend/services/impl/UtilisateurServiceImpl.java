package com.dp.dpshopbackend.services.impl;

import com.dp.dpshopbackend.dto.StateDto;
import com.dp.dpshopbackend.dto.UtilisateurDto;
import com.dp.dpshopbackend.exceptions.ResourceNotFoundException;
import com.dp.dpshopbackend.models.State;
import com.dp.dpshopbackend.models.Utilisateur;
import com.dp.dpshopbackend.repository.UtilisateurRepository;
import com.dp.dpshopbackend.services.UtilisateurService;
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
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto utilisateurDto) {

        return UtilisateurDto.fromEntityToDto(
                utilisateurRepository.save(
                        UtilisateurDto.fromDtoToEntity(utilisateurDto)
                )
        );
    }

    @Override
    public UtilisateurDto update(Long id, UtilisateurDto utilisateurDto) {
        if (!utilisateurRepository.existsById(id)) {
            throw new ResourceNotFoundException("State not found");
        }

        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(id);

        if (!optionalUtilisateur.isPresent()) {
            throw new ResourceNotFoundException("State not found");
        }

        UtilisateurDto utilisateurDtoResult = UtilisateurDto.fromEntityToDto(optionalUtilisateur.get());

        utilisateurDtoResult.setName(utilisateurDto.getName());
        utilisateurDtoResult.setUsername(utilisateurDto.getUsername());
        utilisateurDtoResult.setEmail(utilisateurDto.getEmail());
        utilisateurDtoResult.setMobile(utilisateurDto.getMobile());
        utilisateurDtoResult.setPassword(utilisateurDto.getPassword());

        return UtilisateurDto.fromEntityToDto(
                utilisateurRepository.save(
                        UtilisateurDto.fromDtoToEntity(utilisateurDtoResult)
                )
        );

    }

    @Override
    public UtilisateurDto findById(Long id) {
        if (id == null) {
            log.error("Utilisateur Id is null");
            return null;
        }

        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);

        return Optional.of(UtilisateurDto.fromEntityToDto(utilisateur.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Utilisateur avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public UtilisateurDto findByUsername(String username) {
        if (username == null) {
            log.error("Utilisateur with this username is null");
            return null;
        }

        Optional<Utilisateur> utilisateur = utilisateurRepository.findByUsername(username);

        return Optional.of(UtilisateurDto.fromEntityToDto(utilisateur.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Utilisateur avec l'Id = " + username + "n'a été trouvé")
        );
    }

    @Override
    public List<UtilisateurDto> findByOrderByIdDesc() {
        return utilisateurRepository.findByOrderByIdDesc().stream()
                .map(UtilisateurDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll().stream()
                .map(UtilisateurDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Utilisateur Id is null");
            return;
        }

        utilisateurRepository.deleteById(id);

    }
}

package com.dp.dpshopbackend.services.impl;

import com.dp.dpshopbackend.dto.CategorieDto;
import com.dp.dpshopbackend.dto.ScategorieDto;
import com.dp.dpshopbackend.exceptions.ResourceNotFoundException;
import com.dp.dpshopbackend.models.Categorie;
import com.dp.dpshopbackend.models.Scategorie;
import com.dp.dpshopbackend.repository.CategorieRepository;
import com.dp.dpshopbackend.repository.ScategorieRepository;
import com.dp.dpshopbackend.services.CategorieService;
import com.dp.dpshopbackend.services.ScategorieService;
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
public class ScategorieServiceImpl implements ScategorieService {


    @Autowired
    private final ScategorieRepository scategorieRepository;

    public ScategorieServiceImpl(ScategorieRepository scategorieRepository) {
        this.scategorieRepository = scategorieRepository;
    }

    @Override
    public ScategorieDto save(ScategorieDto scategorieDto) {

        return ScategorieDto.fromEntityToDto(
                scategorieRepository.save(
                        ScategorieDto.fromDtoToEntity(scategorieDto)
                )
        );
    }

    @Override
    public ScategorieDto findById(Long id) {
        if (id == null) {
            log.error("Scategorie Id is null");
            return null;
        }

        Optional<Scategorie> scategorie = scategorieRepository.findById(id);

        return Optional.of(ScategorieDto.fromEntityToDto(scategorie.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun scategorie avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public ScategorieDto findByLibelle(String libelle) {
        if (!StringUtils.hasLength(libelle)) {
            log.error("Scategorie Libelle is null");
        }

        Optional<Scategorie> scategorie = scategorieRepository.findScategorieByLibelle(libelle);

        return Optional.of(ScategorieDto.fromEntityToDto(scategorie.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun scategorie avec l'Id = " + libelle + "n'a été trouvé")
        );


    }

    @Override
    public List<ScategorieDto> findAll() {
        return scategorieRepository.findAll().stream()
                .map(ScategorieDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Scategorie Id is null");
            return;
        }
        scategorieRepository.deleteById(id);

    }
}

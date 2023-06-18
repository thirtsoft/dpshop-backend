package com.dp.dpshopbackend.services.impl;

import com.dp.dpshopbackend.dto.ScategoryDto;
import com.dp.dpshopbackend.exceptions.ResourceNotFoundException;
import com.dp.dpshopbackend.models.Scategory;
import com.dp.dpshopbackend.repository.ScategoryRepository;
import com.dp.dpshopbackend.services.ScategoryService;
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
public class ScategoryServiceImpl implements ScategoryService {

    @Autowired
    private final ScategoryRepository scategoryRepository;

    public ScategoryServiceImpl(ScategoryRepository scategoryRepository) {
        this.scategoryRepository = scategoryRepository;
    }

    @Override
    public ScategoryDto save(ScategoryDto scategoryDto) {
        scategoryDto.setActif(true);
        return ScategoryDto.fromEntityToDto(
                scategoryRepository.save(
                        ScategoryDto.fromDtoToEntity(scategoryDto)
                )
        );
    }

    @Override
    public ScategoryDto update(Long id, ScategoryDto scategoryDto) {
        if (!scategoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Scategory not found");
        }

        Optional<Scategory> scategory = scategoryRepository.findById(id);

        if (!scategory.isPresent()) {
            throw new ResourceNotFoundException("Scategory not found");
        }

        ScategoryDto scategoryResult = ScategoryDto.fromEntityToDto(scategory.get());

        scategoryResult.setCode(scategoryDto.getCode());
        scategoryResult.setLibelle(scategoryDto.getLibelle());
        scategoryResult.setCategoryDto(scategoryDto.getCategoryDto());

        return ScategoryDto.fromEntityToDto(
                scategoryRepository.save(
                        ScategoryDto.fromDtoToEntity(scategoryResult)
                )
        );
    }

    @Override
    public ScategoryDto findById(Long id) {
        if (id == null) {
            log.error("Scategorie Id is null");
            return null;
        }

        Optional<Scategory> scategorie = scategoryRepository.findById(id);

        return Optional.of(ScategoryDto.fromEntityToDto(scategorie.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun scategorie avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public ScategoryDto findByLibelle(String libelle) {
        if (!StringUtils.hasLength(libelle)) {
            log.error("Scategorie Libelle is null");
        }

        Optional<Scategory> scategorie = scategoryRepository.findScategorieByLibelle(libelle);

        return Optional.of(ScategoryDto.fromEntityToDto(scategorie.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun scategorie avec l'Id = " + libelle + "n'a été trouvé")
        );


    }

    @Override
    public List<ScategoryDto> findAll() {
        return scategoryRepository.findAll().stream()
                .map(ScategoryDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ScategoryDto> findByOrderByIdDesc() {
        return scategoryRepository.findByOrderByIdDesc().stream()
                .map(ScategoryDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Scategorie Id is null");
            return;
        }
        scategoryRepository.deleteById(id);
    }

    @Override
    public List<ScategoryDto> findAllActiveSubcategories() {
        return scategoryRepository.findAll().stream()
                .map(ScategoryDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSubcategory(Long subcategoryId) {
        if (subcategoryId == null) {
            log.error("Scategorie Id is null");
            return;
        }
        Optional<Scategory> optionalScategory = scategoryRepository.findById(subcategoryId);
        Scategory scategory = optionalScategory.get();
        scategory.setActif(false);
        scategoryRepository.save(scategory);
    }
}

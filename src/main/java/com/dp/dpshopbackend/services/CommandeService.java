package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.CommandeDto;

import java.util.List;

public interface CommandeService {

    CommandeDto save(CommandeDto commandeDto);

    CommandeDto findById(Long id);

    //  ScategorieDto findByLibelle(String libelle);

    List<CommandeDto> findAll();

    void delete(Long id);
}

package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.AddressLivraisonDto;

import java.util.List;

public interface AddressLivraisonService {

    AddressLivraisonDto save(AddressLivraisonDto addressLivraisonDto);

    AddressLivraisonDto findById(Long id);

    //  ScategorieDto findByLibelle(String libelle);

    List<AddressLivraisonDto> findAll();

    void delete(Long id);
}

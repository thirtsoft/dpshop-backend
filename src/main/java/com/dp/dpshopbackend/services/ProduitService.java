package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.ProduitDto;

import java.util.List;

public interface ProduitService {

    ProduitDto save(ProduitDto produitDto);

    ProduitDto findById(Long id);

    ProduitDto findByReference(String reference);

    List<ProduitDto> findAll();

    void delete(Long id);
}

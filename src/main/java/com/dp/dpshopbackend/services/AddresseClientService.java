package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.AddressClientDto;
import com.dp.dpshopbackend.models.Utilisateur;

import java.util.List;

public interface AddresseClientService {

    AddressClientDto save(AddressClientDto addressClientDto);

    AddressClientDto findById(Long id);

    List<AddressClientDto> findAll();

    List<AddressClientDto> findByOrderByIdDesc();

    void delete(Long id);

}

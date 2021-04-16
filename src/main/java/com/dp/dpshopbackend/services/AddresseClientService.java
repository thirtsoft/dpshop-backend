package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.AddressClientDto;

import java.util.List;

public interface AddresseClientService {

    AddressClientDto save(AddressClientDto addressClientDto);

    AddressClientDto findById(Long id);

    List<AddressClientDto> findAll();

    void delete(Long id);

}

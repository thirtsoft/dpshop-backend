package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.ClientDto;

import java.util.List;

public interface ClientService {

    ClientDto save(ClientDto clientDto);

    ClientDto update(Long idClient, ClientDto clientDto);

    ClientDto findById(Long id);

    ClientDto findByReference(String reference);

    List<ClientDto> findAll();

    void delete(Long id);
}

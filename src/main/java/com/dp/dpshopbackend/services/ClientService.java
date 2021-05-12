package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.ClientDto;

import java.util.List;

public interface ClientService {

    ClientDto save(ClientDto clientDto);

    ClientDto findById(Long id);

    List<ClientDto> findAll();

    void delete(Long id);
}

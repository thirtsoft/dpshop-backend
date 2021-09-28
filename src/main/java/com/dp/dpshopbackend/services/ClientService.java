package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.ClientDto;

import java.math.BigDecimal;
import java.util.List;

public interface ClientService {

    ClientDto save(ClientDto clientDto);

    ClientDto update(Long idClient, ClientDto clientDto);

    ClientDto findById(Long id);

    List<ClientDto> findAll();

    BigDecimal countNumberOfClient();

    void delete(Long id);
}

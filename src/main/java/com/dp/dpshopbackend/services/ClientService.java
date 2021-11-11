package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.CategoryDto;
import com.dp.dpshopbackend.dto.ClientDto;

import java.math.BigDecimal;
import java.util.List;

public interface ClientService {

    ClientDto save(ClientDto clientDto);

    ClientDto update(Long idClient, ClientDto clientDto);

    ClientDto findById(Long id);

    List<ClientDto> findAll();

    List<ClientDto> findByOrderByIdDesc();

    BigDecimal countNumberOfClient();

    void delete(Long id);
}

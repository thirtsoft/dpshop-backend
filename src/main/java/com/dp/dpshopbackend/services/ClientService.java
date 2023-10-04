package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.ClientDto;

import java.math.BigDecimal;
import java.util.List;

public interface ClientService {

    ClientDto findById(Long id);

    BigDecimal countNumberOfClient();

    List<ClientDto> findAllActiveClients();

    void deleteClient(Long clientId);
}

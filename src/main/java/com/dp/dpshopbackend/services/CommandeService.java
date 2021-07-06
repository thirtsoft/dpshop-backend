package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.CommandeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeService {

    CommandeDto save(CommandeDto commandeDto);

    CommandeDto update(Long comId, CommandeDto commandeDto);

    CommandeDto findById(Long id);

    List<CommandeDto> findAll();

    BigDecimal countNumberOfCommande();

    BigDecimal sumTotalOfCommandesByMonth();

    Page<CommandeDto> findCommandeByCustomerPageables(Long clientId, Pageable pageable);

    void delete(Long id);

}

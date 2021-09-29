package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.CommandeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeService {

    CommandeDto save(CommandeDto commandeDto);

    CommandeDto saveWithAddresses(CommandeDto commandeDto);

    CommandeDto saveWithUtilisateur(Long userId, CommandeDto commandeDto);

    CommandeDto update(Long comId, CommandeDto commandeDto);

    CommandeDto findById(Long id);

    List<CommandeDto> findAll();

    BigDecimal countNumberOfCommande();

    BigDecimal countNumberOfCommandesInMonth();

    BigDecimal sumTotaleOfCommandeByMonth();

    BigDecimal countNumberOfOrdersByStatusPending();

    BigDecimal sumTotalOfCommandesByYear();

    List<CommandeDto> findCommandeByCustomerId(Long userId);

    List<?> countNumberTotalOfCommandeByMonth();

    List<?> sumTotalOfCommandeByMonth();

    List<?> sumTotalOfOrdersByYears();

    Page<CommandeDto> findCommandeByCustomerPageables(Long clientId, Pageable pageable);

    Page<CommandeDto> findCommandeByUtilisateurPageables(Long userId, Pageable pageable);

    void delete(Long id);

}

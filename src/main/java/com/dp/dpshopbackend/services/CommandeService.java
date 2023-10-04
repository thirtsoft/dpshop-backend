package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.CommandeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeService {

    CommandeDto updateStatusOfCommande(String status, String id);

    CommandeDto findById(Long id);

    List<CommandeDto> findListOrderByStatusPending();

    List<CommandeDto> findListOrderByStatusPayed();

    List<CommandeDto> findListOrderByUserId(Long userId);

    BigDecimal countNumberOfCommande();

    BigDecimal countNumberOfCommandesInMonth();

    BigDecimal sumTotalOfCommandeByDay();

    BigDecimal sumTotaleOfCommandeByMonth();

    BigDecimal countNumberOfOrdersByStatusPending();

    BigDecimal sumTotalOfCommandesByYear();

    List<?> countNumberOfCommandeByDay();

    List<?> countNumberTotalOfCommandeByMonth();

    List<?> sumTotalOfCommandeByMonth();

    List<?> sumTotalOfOrdersByYears();

    List<CommandeDto> findCommandesByUserOrderByIdDesc(Long userId);

    List<CommandeDto> findCommandesByAddressLivraisonId(Long addLivraison);

    List<CommandeDto> findCommandesByAddressAchatId(Long addAchat);

    Page<CommandeDto> findCommandeByUtilisateurPageables(Long userId, Pageable pageable);

    List<CommandeDto> findAllActiveCommandes();

    void deleteCommande(Long commandeId);

}

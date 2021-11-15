package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.CommandeDto;
import com.dp.dpshopbackend.enumeration.StatusCommande;
import com.dp.dpshopbackend.models.Commande;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeService {

    CommandeDto save(CommandeDto commandeDto);

    CommandeDto saveWithAddresses(CommandeDto commandeDto);

    CommandeDto saveWithUtilisateur(Long userId, CommandeDto commandeDto);

    CommandeDto update(Long comId, CommandeDto commandeDto);

    CommandeDto updateStatusOfCommande(StatusCommande statusCommande, String id);

    CommandeDto findById(Long id);

    List<CommandeDto> findAll();

    List<CommandeDto> findByOrderByIdDesc();

    List<CommandeDto> findListOrderByStatusPending();

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

    void delete(Long id);

}

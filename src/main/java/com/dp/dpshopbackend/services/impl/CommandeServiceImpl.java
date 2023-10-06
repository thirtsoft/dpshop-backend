package com.dp.dpshopbackend.services.impl;

import com.dp.dpshopbackend.dto.CommandeDto;
import com.dp.dpshopbackend.exceptions.ResourceNotFoundException;
import com.dp.dpshopbackend.models.Commande;
import com.dp.dpshopbackend.repository.*;
import com.dp.dpshopbackend.services.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepository commandeRepository;
    private final LigneCommandeService ligneCommandeService;
    private final ArticleService articleService;
    private final ClientService clientService;
    private final UtilisateurService utilisateurService;
    private final LigneCommandeRepository ligneCommandeRepository;
    private final ClientRepository clientRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final ArticleRepository articleRepository;
    private final String status = "ENCOURS";
    double total = 0;
    Logger logger = LoggerFactory.getLogger(CommandeServiceImpl.class);

    @Autowired
    public CommandeServiceImpl(CommandeRepository commandeRepository,
                               LigneCommandeService ligneCommandeService,
                               ArticleService articleService,
                               ClientService clientService,
                               UtilisateurService utilisateurService,
                               LigneCommandeRepository ligneCommandeRepository,
                               ClientRepository clientRepository,
                               ArticleRepository articleRepository,
                               UtilisateurRepository utilisateurRepository
    ) {
        this.commandeRepository = commandeRepository;
        this.ligneCommandeService = ligneCommandeService;
        this.articleService = articleService;
        this.clientService = clientService;
        this.utilisateurService = utilisateurService;
        this.ligneCommandeRepository = ligneCommandeRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public CommandeDto updateStatusOfCommande(String status, String id) {
        Optional<Commande> commandeOptional = commandeRepository.findById(Long.valueOf(id));
        CommandeDto commandeDtoResult = CommandeDto.fromEntityToDto(commandeOptional.get());
        commandeDtoResult.setStatus(status);
        return CommandeDto.fromEntityToDto(
                commandeRepository.save(
                        CommandeDto.fromDtoToEntity(commandeDtoResult)
                )
        );
    }

    @Override
    public CommandeDto findById(Long id) {
        if (id == null) {
            log.error("Commande Id is null");
            return null;
        }
        Optional<Commande> commande = commandeRepository.findById(id);
        return Optional.of(CommandeDto.fromEntityToDto(commande.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Commande avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<CommandeDto> findListOrderByStatusPending() {
        return commandeRepository.findListOrderByStatusPending().stream()
                .map(CommandeDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeDto> findListOrderByStatusPayed() {
        return commandeRepository.findListOrderByStatusPayed().stream()
                .map(CommandeDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeDto> findListOrderByUserId(Long userId) {
        return null;
    }

    @Override
    public BigDecimal countNumberOfCommande() {
        return commandeRepository.countNumberOfCommande();
    }

    @Override
    public BigDecimal countNumberOfCommandesInMonth() {
        return commandeRepository.countNumberOfOrdersInMonth();
    }

    @Override
    public BigDecimal sumTotalOfCommandeByDay() {
        return commandeRepository.sumTotalOfCommandeByDay();
    }

    @Override
    public BigDecimal sumTotaleOfCommandeByMonth() {
        return commandeRepository.sumTotaleOfCommandeByMonth();
    }

    @Override
    public BigDecimal countNumberOfOrdersByStatusPending() {
        return commandeRepository.countNumberOfOrdersByStatusPending();
    }

    @Override
    public BigDecimal sumTotalOfCommandesByYear() {
        return commandeRepository.sumTotalOfCommandesByYear();
    }

    @Override
    public List<?> countNumberOfCommandeByDay() {
        return commandeRepository.countNumberOfCommandeByDay()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<?> countNumberTotalOfCommandeByMonth() {
        return commandeRepository.countNumberOfCommandeByMonth()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<?> sumTotalOfCommandeByMonth() {
        return commandeRepository.sumTotalOfCommandeByMonth()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<?> sumTotalOfOrdersByYears() {
        return commandeRepository.sumTotalOfCommandeByYears()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeDto> findCommandesByUserOrderByIdDesc(Long userId) {
        return commandeRepository.ListCommandeByCustomerId(userId).stream()
                .map(CommandeDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeDto> findCommandesByAddressLivraisonId(Long addLivraison) {
        return commandeRepository.ListCommandeByAddressLivraisonId(addLivraison).stream()
                .map(CommandeDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeDto> findCommandesByAddressAchatId(Long addAchat) {
        return commandeRepository.ListCommandeByAddressAchatId(addAchat).stream()
                .map(CommandeDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<CommandeDto> findCommandeByUtilisateurPageables(Long userId, Pageable pageable) {
        return commandeRepository.findCommandeByUtilisateurPageables(userId, pageable)
                .map(CommandeDto::fromEntityToDto);
    }

    @Override
    public List<CommandeDto> findAllActiveCommandes() {
        return commandeRepository.findAll().stream()
                .map(CommandeDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCommande(Long commandeId) {
        if (commandeId == null) {
            log.error("Commande Id is null");
            return;
        }
        Optional<Commande> commandeOptional = commandeRepository.findById(commandeId);
        Commande commande = commandeOptional.get();
        commande.setActif(false);
        commandeRepository.save(commande);
        if (commande.getLcomms() != null) {
            commande.getLcomms().forEach(ligCmdClt -> {
                ligCmdClt.setActif(false);
                ligneCommandeRepository.save(ligCmdClt);
            });
        }
    }
}

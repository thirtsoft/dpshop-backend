package com.dp.dpshopbackend.services.impl;

import com.dp.dpshopbackend.dto.ArticleDto;
import com.dp.dpshopbackend.dto.ClientDto;
import com.dp.dpshopbackend.dto.CommandeDto;
import com.dp.dpshopbackend.dto.LigneCommandeDto;
import com.dp.dpshopbackend.enumeration.StatusCommande;
import com.dp.dpshopbackend.exceptions.ResourceNotFoundException;
import com.dp.dpshopbackend.models.Article;
import com.dp.dpshopbackend.models.Client;
import com.dp.dpshopbackend.models.Commande;
import com.dp.dpshopbackend.models.LigneCommande;
import com.dp.dpshopbackend.repository.CommandeRepository;
import com.dp.dpshopbackend.services.ArticleService;
import com.dp.dpshopbackend.services.ClientService;
import com.dp.dpshopbackend.services.CommandeService;
import com.dp.dpshopbackend.services.LigneCommandeService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class CommandeServiceImpl implements CommandeService {

    @Autowired
    private final CommandeRepository commandeRepository;

    @Autowired
    private final LigneCommandeService ligneCommandeService;

    @Autowired
    private final ArticleService articleService;

    @Autowired
    private final ClientService clientService;

    Logger logger = LoggerFactory.getLogger(CommandeServiceImpl.class);

    public CommandeServiceImpl(CommandeRepository commandeRepository,
                               LigneCommandeService ligneCommandeService,
                               ArticleService articleService,
                               ClientService clientService) {
        this.commandeRepository = commandeRepository;
        this.ligneCommandeService = ligneCommandeService;
        this.articleService = articleService;
        this.clientService = clientService;
    }

    @Override
    public CommandeDto save(CommandeDto commandeDto) {
        System.out.println("Initial Numero Commande " + commandeDto.getNumeroCommande());
        logger.info("CommandeDto {}", commandeDto);

        List<LigneCommandeDto> ligneCommandeDtoList = commandeDto.getLcomms();

        if (ligneCommandeDtoList == null || ligneCommandeDtoList.size() == 0) {
            throw new IllegalArgumentException("Vous devez au moins commander un produit");
        }
        ClientDto clientOptional = clientService.findById(commandeDto.getClientDto().getId());
        if (clientOptional == null) {
            log.warn("Client with ID {} was not found in the DB", commandeDto.getClientDto().getId());
            throw new IllegalArgumentException("Vous devez selectionner un client");
        }

        if (commandeDto.getLcomms() != null) {
            commandeDto.getLcomms().forEach(ligCmdClt -> {
                if (ligCmdClt.getArticleDto() != null) {
                    ArticleDto articleDto = articleService.findById(ligCmdClt.getArticleDto().getId());
                    if (articleDto == null) {
                        log.warn("L'article avec l'ID " + ligCmdClt.getArticleDto().getId() + " n'existe pas");
                    }
                } else {
                    log.warn("Impossible d'enregister une commande avec un aticle NULL");
                }
            });
        }

        Commande savedCmdClt = commandeRepository.save(CommandeDto.fromDtoToEntity(commandeDto));

        if (commandeDto.getLcomms() != null) {
            commandeDto.getLcomms().forEach(ligCmdClt -> {
                LigneCommande ligneCommande = LigneCommandeDto.fromDtoToEntity(ligCmdClt);
                ligneCommande.setCommande(savedCmdClt);
                ligneCommandeService.save(LigneCommandeDto.fromEntityToDto(ligneCommande));
            });
        }

        return CommandeDto.fromEntityToDto(savedCmdClt);


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
    public List<CommandeDto> findAll() {
        return commandeRepository.findAll().stream()
                .map(CommandeDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Commande Id is null");
            return;
        }
        commandeRepository.deleteById(id);

    }
}

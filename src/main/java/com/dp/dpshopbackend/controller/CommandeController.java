package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.CommandeApi;
import com.dp.dpshopbackend.dto.CommandeDto;
import com.dp.dpshopbackend.services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin
public class CommandeController implements CommandeApi {

    private final CommandeService commandeService;

    @Autowired
    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @Override
    public ResponseEntity<CommandeDto> save(CommandeDto commandeDto) {
        return ResponseEntity.ok(commandeService.save(commandeDto));
    }

    @Override
    public ResponseEntity<CommandeDto> saveWithAddresses(CommandeDto commandeDto) {
        return ResponseEntity.ok(commandeService.saveWithAddresses(commandeDto));
    }

    @Override
    public ResponseEntity<CommandeDto> update(Long id, CommandeDto commandeDto) {
        commandeDto.setId(id);
        return ResponseEntity.ok(commandeService.save(commandeDto));
    }

    @Override
    public ResponseEntity<CommandeDto> findById(Long id) {
        return ResponseEntity.ok(commandeService.findById(id));
    }

    @Override
    public BigDecimal countNumberOfCommande() {
        return commandeService.countNumberOfCommande();
    }

    @Override
    public BigDecimal sumTotaleOfCommandeByMonth() {
        return commandeService.sumTotaleOfCommandeByMonth();
    }

    @Override
    public BigDecimal sumTotaleOfCommandeByYear() {
        return commandeService.sumTotalOfCommandesByYear();
    }

    @Override
    public List<CommandeDto> findAll() {
        return commandeService.findAll();
    }

    @Override
    public List<?> countNumberOfCommandeByMonth() {
        return commandeService.countNumberTotalOfCommandeByMonth();
    }

    @Override
    public List<?> getSumTotaleOfCommandeByMonth() {
        return commandeService.sumTotalOfCommandeByMonth();
    }

    @Override
    public Page<CommandeDto> getListCommandeByCustomerByPageables(Long clientId, int page, int size) {
        final Pageable pageable = PageRequest.of(page, size);
        return commandeService.findCommandeByCustomerPageables(clientId, pageable);
    }

    @Override
    public Page<CommandeDto> getListCommandeByUtilisateurByPageables(Long userId, int page, int size) {
        final Pageable pageable = PageRequest.of(page, size);
        return commandeService.findCommandeByUtilisateurPageables(userId, pageable);
    }


    @Override
    public void delete(Long id) {
        commandeService.delete(id);
    }
}

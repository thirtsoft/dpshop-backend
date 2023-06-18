package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.CommandeApi;
import com.dp.dpshopbackend.dto.CommandeDto;
import com.dp.dpshopbackend.dto.UtilisateurDto;
import com.dp.dpshopbackend.models.Utilisateur;
import com.dp.dpshopbackend.services.CommandeService;
import com.dp.dpshopbackend.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CommandeController implements CommandeApi {

    private final CommandeService commandeService;

    private final UtilisateurService utilisateurService;

    @Autowired
    public CommandeController(CommandeService commandeService, UtilisateurService utilisateurService) {
        this.commandeService = commandeService;
        this.utilisateurService = utilisateurService;
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
    public ResponseEntity<CommandeDto> saveWithLoginUser(CommandeDto commandeDto, Long id) {

        Utilisateur userInfo = Optional.of(UtilisateurDto.fromDtoToEntity(utilisateurService.findById(id))).get();

        UtilisateurDto userInfoDto = UtilisateurDto.fromEntityToDto(userInfo);

        //    commandeDto.setUtilisateurDto(userInfoDto);
        commandeService.save(commandeDto);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<CommandeDto> update(Long id, CommandeDto commandeDto) {
        commandeDto.setId(id);
        return ResponseEntity.ok(commandeService.save(commandeDto));
    }

    @Override
    public ResponseEntity<CommandeDto> updateStatusOfCommande(String status, String id) {
        CommandeDto newCommandeDto = commandeService.updateStatusOfCommande(status, id);
        return new ResponseEntity<>(newCommandeDto, HttpStatus.OK);
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
    public BigDecimal countNumberOfOrdersInMonth() {
        return commandeService.countNumberOfCommandesInMonth();
    }

    @Override
    public BigDecimal countNumberOfOrdersByStatusPending() {
        return commandeService.countNumberOfOrdersByStatusPending();
    }

    @Override
    public BigDecimal sumTotaleOfCommandeByDay() {
        return commandeService.sumTotalOfCommandeByDay();
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
    public ResponseEntity<List<CommandeDto>> findAll() {
        List<CommandeDto> commandeDtoList = commandeService.findAll();
        return new ResponseEntity<>(commandeDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CommandeDto>> getAllCommandesOrderByIdDesc() {
        List<CommandeDto> commandeDtoList = commandeService.findByOrderByIdDesc();
        return new ResponseEntity<>(commandeDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CommandeDto>> getListOrderByStatusPending() {
        List<CommandeDto> commandeDtoList = commandeService.findListOrderByStatusPending();
        return new ResponseEntity<>(commandeDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CommandeDto>> getListOrderByStatusPayed() {
        List<CommandeDto> commandeDtoList = commandeService.findListOrderByStatusPayed();
        return new ResponseEntity<>(commandeDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CommandeDto>> getCommandesByUserOrderByIdDesc(Long id) {
        List<CommandeDto> commandeDtoList = commandeService.findCommandesByUserOrderByIdDesc(id);
        return new ResponseEntity<>(commandeDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CommandeDto>> getCommandesByBillingAddressOrderByIdDesc(Long id) {
        List<CommandeDto> commandeDtoList = commandeService.findCommandesByAddressAchatId(id);
        return new ResponseEntity<>(commandeDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CommandeDto>> getCommandesByShippingAddressByIdDesc(Long id) {
        List<CommandeDto> commandeDtoList = commandeService.findCommandesByAddressLivraisonId(id);
        return new ResponseEntity<>(commandeDtoList, HttpStatus.OK);
    }

    @Override
    public List<?> countNumberOfCommandeByDay() {
        List<?> commandeDtoList = commandeService.countNumberOfCommandeByDay();
        return commandeDtoList;
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
    public List<?> getSumTotalOfOrdersByYears() {
        return commandeService.sumTotalOfOrdersByYears();
    }

    @Override
    public Page<CommandeDto> getCommandesByUtilisateurIdByPageables(Long userId, int page, int size) {
        final Pageable pageable = PageRequest.of(page, size);
        return commandeService.findCommandeByUtilisateurPageables(userId, pageable);
    }

    @Override
    public void delete(Long id) {
        commandeService.delete(id);
    }

    @Override
    public ResponseEntity<List<CommandeDto>> getAllActiveCommandes() {
        List<CommandeDto> commandeDtoList = commandeService.findAllActiveCommandes();
        return new ResponseEntity<>(commandeDtoList, HttpStatus.OK);
    }

    @Override
    public void deleteCommande(Long idCommande) {
        commandeService.deleteCommande(idCommande);
    }
}

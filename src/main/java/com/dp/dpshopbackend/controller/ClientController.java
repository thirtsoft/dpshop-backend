package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.ClientApi;
import com.dp.dpshopbackend.dto.ClientDto;
import com.dp.dpshopbackend.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin(origins = "https://soulbusinesse.com")
@RestController
public class ClientController implements ClientApi {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public ResponseEntity<ClientDto> save(ClientDto clientDto) {
        return ResponseEntity.ok(clientService.save(clientDto));
    }

    @Override
    public ResponseEntity<ClientDto> update(Long id, ClientDto clientDto) {
        clientDto.setId(id);
        return ResponseEntity.ok(clientService.save(clientDto));
    }

    @Override
    public ResponseEntity<ClientDto> findById(Long id) {
        return ResponseEntity.ok(clientService.findById(id));
    }


    @Override
    public ResponseEntity<List<ClientDto>> findAll() {
        List<ClientDto> clientDtoList = clientService.findAll();
        return new ResponseEntity<>(clientDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ClientDto>> getAllClientsOrderByIdDesc() {
        List<ClientDto> clientDtoList = clientService.findByOrderByIdDesc();
        return new ResponseEntity<>(clientDtoList, HttpStatus.OK);
    }

    @Override
    public BigDecimal countNumberOfClient() {
        return clientService.countNumberOfClient();
    }

    @Override
    public void delete(Long id) {
        clientService.delete(id);
    }

    @Override
    public ResponseEntity<List<ClientDto>> getAllActiveClients() {
        List<ClientDto> clientDtoList = clientService.findAllActiveClients();
        return new ResponseEntity<>(clientDtoList, HttpStatus.OK);
    }

    @Override
    public void deleteClient(Long idClient) {
        clientService.deleteClient(idClient);
    }
}

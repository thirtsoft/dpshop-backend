package com.dp.dpshopbackend.services.impl;

import com.dp.dpshopbackend.dto.ClientDto;
import com.dp.dpshopbackend.exceptions.ResourceNotFoundException;
import com.dp.dpshopbackend.models.Category;
import com.dp.dpshopbackend.models.Client;
import com.dp.dpshopbackend.repository.ClientRepository;
import com.dp.dpshopbackend.services.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDto findById(Long id) {
        if (id == null) {
            log.error("Client Id is null");
            return null;
        }
        Optional<Client> client = clientRepository.findById(id);
        return Optional.of(ClientDto.fromEntityToDto(client.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Client avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public BigDecimal countNumberOfClient() {
        return clientRepository.countNumberOfClient();
    }

    @Override
    public List<ClientDto> findAllActiveClients() {
        return clientRepository.findAll().stream()
                .map(ClientDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteClient(Long clientId) {
        if (clientId == null) {
            log.error("Client Id is null");
            return;
        }
        Optional<Client> clientOptional = clientRepository.findById(clientId);
        Client client = clientOptional.get();
        client.setActif(false);
        clientRepository.save(client);
    }
}

package com.dp.dpshopbackend.services.impl;

import com.dp.dpshopbackend.dto.ClientDto;
import com.dp.dpshopbackend.exceptions.ResourceNotFoundException;
import com.dp.dpshopbackend.models.Client;
import com.dp.dpshopbackend.repository.ClientRepository;
import com.dp.dpshopbackend.services.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
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
    public ClientDto save(ClientDto clientDto) {

        return ClientDto.fromEntityToDto(
                clientRepository.save(
                        ClientDto.fromDtoToEntity(clientDto)
                )
        );
    }

    @Override
    public ClientDto update(Long idClient, ClientDto clientDto) {
        if (!clientRepository.existsById(idClient)) {
            throw new ResourceNotFoundException("Client not found");
        }

        Optional<Client> clientOptional = clientRepository.findById(idClient);

        if (!clientOptional.isPresent()) {
            throw new ResourceNotFoundException("Client not found");
        }

        ClientDto clientDtoResult = ClientDto.fromEntityToDto(clientOptional.get());

        clientDtoResult.setFirstName(clientDto.getFirstName());
        clientDtoResult.setLastName(clientDto.getLastName());
        clientDtoResult.setMobile(clientDto.getMobile());
        clientDtoResult.setEmail(clientDto.getEmail());

        return ClientDto.fromEntityToDto(
                clientRepository.save(
                        ClientDto.fromDtoToEntity(clientDtoResult)
                )
        );
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

   /* @Override
    public ClientDto findByReference(String reference) {
        if (!StringUtils.hasLength(reference)) {
            log.error("Client REFERENCE is null");
        }

        Optional<Client> clientOptional = clientRepository.findClientByReference(reference);

        return Optional.of(ClientDto.fromEntityToDto(clientOptional.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Client avec l'Id = " + reference + "n'a été trouvé")
        );
    }*/

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream()
                .map(ClientDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Client Id is null");
            return;
        }

        clientRepository.deleteById(id);

    }
}

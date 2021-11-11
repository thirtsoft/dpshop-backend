package com.dp.dpshopbackend.services.impl;

import com.dp.dpshopbackend.dto.AddressClientDto;
import com.dp.dpshopbackend.exceptions.ResourceNotFoundException;
import com.dp.dpshopbackend.models.AddressClient;
import com.dp.dpshopbackend.repository.AddresseClientRepository;
import com.dp.dpshopbackend.services.AddresseClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class AddresseClientServiceImpl implements AddresseClientService {


    @Autowired
    private final AddresseClientRepository addresseClientRepository;

    public AddresseClientServiceImpl(AddresseClientRepository addresseClientRepository) {
        this.addresseClientRepository = addresseClientRepository;
    }

    @Override
    public AddressClientDto save(AddressClientDto addressClientDto) {

        return AddressClientDto.fromEntityToDto(
                addresseClientRepository.save(
                        AddressClientDto.fromDtoToEntity(addressClientDto)
                )
        );
    }

    @Override
    public AddressClientDto findById(Long id) {
        if (id == null) {
            log.error("AddressClient Id is null");
            return null;
        }

        Optional<AddressClient> addresseClient = addresseClientRepository.findById(id);

        return Optional.of(AddressClientDto.fromEntityToDto(addresseClient.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun AddressClient avec l'Id = " + id + "n'a été trouvé")
        );
    }


    @Override
    public List<AddressClientDto> findAll() {
        return addresseClientRepository.findAll().stream()
                .map(AddressClientDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AddressClientDto> findByOrderByIdDesc() {
        return addresseClientRepository.findByOrderByIdDesc().stream()
                .map(AddressClientDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("AddressClient Id is null");
            return;
        }
        addresseClientRepository.deleteById(id);

    }
}

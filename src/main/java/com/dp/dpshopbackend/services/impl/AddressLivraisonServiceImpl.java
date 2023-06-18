package com.dp.dpshopbackend.services.impl;

import com.dp.dpshopbackend.dto.AddressLivraisonDto;
import com.dp.dpshopbackend.exceptions.ResourceNotFoundException;
import com.dp.dpshopbackend.models.AddressLivraison;
import com.dp.dpshopbackend.models.Article;
import com.dp.dpshopbackend.repository.AddressLivraisonRepository;
import com.dp.dpshopbackend.services.AddressLivraisonService;
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
public class AddressLivraisonServiceImpl implements AddressLivraisonService {


    @Autowired
    private final AddressLivraisonRepository addressLivraisonRepository;

    public AddressLivraisonServiceImpl(AddressLivraisonRepository addressLivraisonRepository) {
        this.addressLivraisonRepository = addressLivraisonRepository;
    }

    @Override
    public AddressLivraisonDto save(AddressLivraisonDto addressLivraisonDto) {
        addressLivraisonDto.setActif(true);
        return AddressLivraisonDto.fromEntityToDto(
                addressLivraisonRepository.save(
                        AddressLivraisonDto.fromDtoToEntity(addressLivraisonDto)
                )
        );
    }

    @Override
    public AddressLivraisonDto update(Long idAddress, AddressLivraisonDto addressLivraisonDto) {
        if (!addressLivraisonRepository.existsById(idAddress)) {
            throw new ResourceNotFoundException("AddressLivraison not found");
        }

        Optional<AddressLivraison> addressLivraisonOptional = addressLivraisonRepository.findById(idAddress);

        if (!addressLivraisonOptional.isPresent()) {
            throw new ResourceNotFoundException("AddressLivraison not found");
        }

        AddressLivraisonDto addressLivraisonDtoResult = AddressLivraisonDto.fromEntityToDto(addressLivraisonOptional.get());
        addressLivraisonDtoResult.setReference(addressLivraisonDto.getReference());
        addressLivraisonDtoResult.setPhone(addressLivraisonDto.getPhone());
        addressLivraisonDtoResult.setZipcode(addressLivraisonDto.getZipcode());
        addressLivraisonDtoResult.setRue(addressLivraisonDto.getRue());
        addressLivraisonDtoResult.setCity(addressLivraisonDto.getCity());
        addressLivraisonDtoResult.setCountry(addressLivraisonDto.getCountry());
        addressLivraisonDtoResult.setStateDto(addressLivraisonDto.getStateDto());

        return AddressLivraisonDto.fromEntityToDto(
                addressLivraisonRepository.save(
                        AddressLivraisonDto.fromDtoToEntity(addressLivraisonDtoResult)
                )
        );
    }

    @Override
    public AddressLivraisonDto findById(Long id) {
        if (id == null) {
            log.error("AddressLivraison Id is null");
            return null;
        }

        Optional<AddressLivraison> addressLivraison = addressLivraisonRepository.findById(id);

        return Optional.of(AddressLivraisonDto.fromEntityToDto(addressLivraison.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun AddressLivraison avec l'Id = " + id + "n'a été trouvé")
        );
    }


    @Override
    public List<AddressLivraisonDto> findAll() {
        return addressLivraisonRepository.findAll().stream()
                .map(AddressLivraisonDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AddressLivraisonDto> findByOrderByIdDesc() {
        return addressLivraisonRepository.findByOrderByIdDesc().stream()
                .map(AddressLivraisonDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("AddressLivraison Id is null");
            return;
        }
        addressLivraisonRepository.deleteById(id);

    }

    @Override
    public List<AddressLivraisonDto> findAllActiveAddressLivraisons() {
        return addressLivraisonRepository.findAll().stream()
                .map(AddressLivraisonDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAddressLivraison(Long AddressLivraisonId) {
        if (AddressLivraisonId == null) {
            log.error("AddressLivraison Id is null");
            return;
        }
        Optional<AddressLivraison> addressLivraisonOptional = addressLivraisonRepository.findById(AddressLivraisonId);
        AddressLivraison addressLivraison = addressLivraisonOptional.get();
        addressLivraison.setActif(false);
        addressLivraisonRepository.save(addressLivraison);
    }
}

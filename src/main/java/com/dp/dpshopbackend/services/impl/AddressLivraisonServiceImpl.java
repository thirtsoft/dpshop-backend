package com.dp.dpshopbackend.services.impl;

import com.dp.dpshopbackend.dto.AddressLivraisonDto;
import com.dp.dpshopbackend.dto.CategorieDto;
import com.dp.dpshopbackend.exceptions.ResourceNotFoundException;
import com.dp.dpshopbackend.models.AddressLivraison;
import com.dp.dpshopbackend.models.Categorie;
import com.dp.dpshopbackend.repository.AddressLivraisonRepository;
import com.dp.dpshopbackend.repository.CategorieRepository;
import com.dp.dpshopbackend.services.AddressLivraisonService;
import com.dp.dpshopbackend.services.CategorieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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

        return AddressLivraisonDto.fromEntityToDto(
                addressLivraisonRepository.save(
                        AddressLivraisonDto.fromDtoToEntity(addressLivraisonDto)
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
    public void delete(Long id) {
        if (id == null) {
            log.error("AddressLivraison Id is null");
            return;
        }
        addressLivraisonRepository.deleteById(id);

    }
}

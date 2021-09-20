package com.dp.dpshopbackend.services.impl;

import com.dp.dpshopbackend.dto.*;
import com.dp.dpshopbackend.dto.checkout.Purchase;
import com.dp.dpshopbackend.models.Client;
import com.dp.dpshopbackend.models.Commande;
import com.dp.dpshopbackend.models.LigneCommande;
import com.dp.dpshopbackend.repository.ClientRepository;
import com.dp.dpshopbackend.services.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CheckoutServiceImpl implements CheckoutService {

    private final ClientRepository clientRepository;

    @Autowired
    public CheckoutServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public PurchaseResponse placeOrder(PurchaseDto purchaseDto) {

        // retrieve the order info from dto
        //    Commande commande = purchaseDto.getCommande();
        CommandeDto commandeDto = purchaseDto.getCommandeDto();

        // generated tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        //    commande.setOrderTrackingNumber(orderTrackingNumber);
        commandeDto.setOrderTrackingNumber(orderTrackingNumber);

        // populate order with orderItems
        List<LigneCommandeDto> ligneCommandeDtos = purchaseDto.getLigneCommandeListDtos();
        ligneCommandeDtos.forEach(item -> commandeDto.add(item));
    /*
        List<LigneCommande> ligneCommandeList = purchaseDto.getLigneCommandeList();
        ligneCommandeList.forEach(item -> commande.add(item));*/

        // populate order with shippingAddress and billingAddress
        commandeDto.setBillingAddressDto(purchaseDto.getBillingAddressDto());
        commandeDto.setShippingAddressDto(purchaseDto.getShippingAddressDto());

        /*
        commande.setBillingAddress(purchaseDto.getBillingAddress());
        commande.setShippingAddress(purchaseDto.getShippingAddress());*/

        // populate custom with order
        ClientDto clientDto = purchaseDto.getClientDto();
        clientDto.add(commandeDto);
/*
        Client client = purchaseDto.getClient();
        client.add(commande);*/

        // save to the database
        //    clientRepository.save(client);

        ClientDto.fromEntityToDto(
                clientRepository.save(
                        ClientDto.fromDtoToEntity(clientDto)
                )
        );

        // return a response
        return new PurchaseResponse(orderTrackingNumber);
    }

    @Override
    public PurchaseResponse placeToOrder(Purchase purchase) {

        System.out.println(purchase);
        // retrieve the order from dto
        Commande commande = purchase.getCommande();

        // generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        commande.setOrderTrackingNumber(orderTrackingNumber);

        // populate order with orderItems
        List<LigneCommande> ligneCommandeList = purchase.getLcomms();
        ligneCommandeList.forEach(item -> commande.add(item));

        // populate order with shippingAddress and billingAddress
        commande.setBillingAddress(purchase.getBillingAddress());
        commande.setShippingAddress(purchase.getShippingAddress());

        // populate customer with order
        Client client = purchase.getClient();
        client.add(commande);

        // save customer to database
        clientRepository.save(client);

        // return response
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        // generate a random UUID (UUID version-4)
        return UUID.randomUUID().toString();
    }
}

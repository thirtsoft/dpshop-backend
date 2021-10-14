package com.dp.dpshopbackend.services.impl;

import com.dp.dpshopbackend.dto.*;
import com.dp.dpshopbackend.dto.checkout.Purchase;
import com.dp.dpshopbackend.enumeration.StatusCommande;
import com.dp.dpshopbackend.models.Client;
import com.dp.dpshopbackend.models.Commande;
import com.dp.dpshopbackend.models.LigneCommande;
import com.dp.dpshopbackend.models.Utilisateur;
import com.dp.dpshopbackend.repository.ClientRepository;
import com.dp.dpshopbackend.repository.UtilisateurRepository;
import com.dp.dpshopbackend.security.service.UserPrinciple;
import com.dp.dpshopbackend.services.CheckoutService;
import com.dp.dpshopbackend.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class CheckoutServiceImpl implements CheckoutService {

    private final ClientRepository clientRepository;

    private final UtilisateurService utilisateurService;

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public CheckoutServiceImpl(ClientRepository clientRepository,
                               UtilisateurService utilisateurService,
                               UtilisateurRepository utilisateurRepository) {
        this.clientRepository = clientRepository;
        this.utilisateurService = utilisateurService;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public PurchaseResponse placeOrder(PurchaseDto purchaseDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        authentication.getName();

        UserPrinciple authUser = (UserPrinciple) authentication.getPrincipal();
        Long userId = authUser.getId();


        Utilisateur utilisateur = Optional.of(UtilisateurDto.fromDtoToEntity(utilisateurService.findById(userId))).get();


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
        //    clientDto.add(commandeDto);

        // populate loggin user with order


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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String login = authentication.getName();

        String currentPrincipalName = authentication.getName();

        //    UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        //    Utilisateur currentUser = utilisateurService.findByUsername(userPrincipal);


        //    Utilisateur user = utilisateurService.findByUsername(login);


        //    Utilisateur utilisateur = Optional.of(UtilisateurDto.fromDtoToEntity(utilisateurService.findByUsername(login))).get();


        System.out.println(purchase);
        // retrieve the order from dto
        Commande commande = purchase.getCommande();

        // generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        commande.setOrderTrackingNumber(orderTrackingNumber);
        commande.setStatusCommande(StatusCommande.ENCOURS);
        commande.setDateCommande(new Date());

        // attach loggin user to order
//        commande.setUtilisateur(utilisateur);

        // populate order with orderItems
        List<LigneCommande> ligneCommandeList = purchase.getLcomms();
        ligneCommandeList.forEach(item -> commande.add(item));

        // populate order with shippingAddress and billingAddress
        commande.setBillingAddress(purchase.getBillingAddress());
        commande.setShippingAddress(purchase.getShippingAddress());

        // populate customer with order
        Client client = purchase.getClient();
        client.add(commande);

        // populate utilisateur with order


        //    Utilisateur utilisateur = purchase.getUtilisateur();
        //    utilisateur.add(commande);

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

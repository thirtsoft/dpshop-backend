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
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
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

    private final String status = "ENCOURS";

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

        CommandeDto commandeDto = purchaseDto.getCommandeDto();

        // generated tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        //    commande.setOrderTrackingNumber(orderTrackingNumber);
        commandeDto.setOrderTrackingNumber(orderTrackingNumber);


        // populate order with orderItems
        List<LigneCommandeDto> ligneCommandeDtos = purchaseDto.getLigneCommandeListDtos();
        ligneCommandeDtos.forEach(item -> commandeDto.add(item));

        // populate order with shippingAddress and billingAddress
        commandeDto.setBillingAddressDto(purchaseDto.getBillingAddressDto());
        commandeDto.setShippingAddressDto(purchaseDto.getShippingAddressDto());

        // populate custom with order
        ClientDto clientDto = purchaseDto.getClientDto();

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

        System.out.println(purchase);
        // retrieve the order from dto
        Commande commande = purchase.getCommande();

        // generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        commande.setOrderTrackingNumber(orderTrackingNumber);
        commande.setStatus(status);
        commande.setDateCommande(new Date());

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

    @Override
    public PurchaseResponse placeToOrderWithUser(Purchase purchase) {

        System.out.println(purchase);
        // retrieve the order from dto
        Commande commande = purchase.getCommande();

        Utilisateur utilisateur = purchase.getUtilisateur();

        // generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        Long numCommande = generateNumeroCommande();
        commande.setOrderTrackingNumber(orderTrackingNumber);
        commande.setNumeroCommande(numCommande);
        commande.setStatus(status);
        commande.setDateCommande(new Date());

        // attach loggin user to order
        commande.setUtilisateur(utilisateur);

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

    public long generateNumeroCommande() {
        final String FORMAT = "yyyyMMddHHmmss";
        return Long.parseLong(DateTimeFormat.forPattern(FORMAT).print(LocalDateTime.now()));
    }
}

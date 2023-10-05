package com.dp.dpshopbackend.services.impl;

import com.dp.dpshopbackend.dto.*;
import com.dp.dpshopbackend.dto.checkout.Purchase;
import com.dp.dpshopbackend.enumeration.StatusCommande;
import com.dp.dpshopbackend.models.*;
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
        String orderTrackingNumber = generateOrderTrackingNumber();
        commandeDto.setOrderTrackingNumber(orderTrackingNumber);
        commandeDto.setActif(true);

      /*  List<LigneCommandeDto> ligneCommandeDtos = purchaseDto.getLigneCommandeListDtos();
        ligneCommandeDtos.forEach(item -> commandeDto.add(item));*/

        commandeDto.setBillingAddressDto(purchaseDto.getBillingAddressDto());
        commandeDto.setShippingAddressDto(purchaseDto.getShippingAddressDto());

        ClientDto clientDto = purchaseDto.getClientDto();
        clientDto.setActif(true);
        ClientDto.fromEntityToDto(
                clientRepository.save(
                        ClientDto.fromDtoToEntity(clientDto)
                )
        );
        return new PurchaseResponse(orderTrackingNumber);
    }

    @Override
    public PurchaseResponse placeToOrder(Purchase purchase) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        String currentPrincipalName = authentication.getName();
        System.out.println(purchase);
        Commande commande = purchase.getCommande();
        String orderTrackingNumber = generateOrderTrackingNumber();
        commande.setOrderTrackingNumber(orderTrackingNumber);
        commande.setStatus(status);
        commande.setDateCommande(new Date());
        commande.setActif(true);

        // populate order with orderItems
        List<LigneCommande> ligneCommandeList = purchase.getLcomms();
        if (ligneCommandeList != null) {
            ligneCommandeList.forEach(ligCmdClt -> {
                ligCmdClt.setActif(true);
                commande.add(ligCmdClt);
            });
        }
        // populate order with shippingAddress and billingAddress
        commande.setBillingAddress(purchase.getBillingAddress());
        commande.setShippingAddress(purchase.getShippingAddress());

        // populate customer with order
        Client client = purchase.getClient();
        client.setActif(true);
        client.add(commande);
        clientRepository.save(client);

        return new PurchaseResponse(orderTrackingNumber);
    }

    @Override
    public PurchaseResponse placeToOrderWithUser(Purchase purchase) {
        Commande commande = purchase.getCommande();
        Utilisateur utilisateur = purchase.getUtilisateur();
        String orderTrackingNumber = generateOrderTrackingNumber();
        Long numCommande = generateNumeroCommande();
        commande.setOrderTrackingNumber(orderTrackingNumber);
        commande.setNumeroCommande(numCommande);
        commande.setStatus(status);
        commande.setDateCommande(new Date());
        commande.setActif(true);
        commande.setUtilisateur(utilisateur);
        List<LigneCommande> ligneCommandeList = purchase.getLcomms();
        if (ligneCommandeList != null) {
            ligneCommandeList.forEach(ligCmdClt -> {
                ligCmdClt.setActif(true);
                commande.add(ligCmdClt);
            });
        }
        AddressLivraison addressLivraison = purchase.getShippingAddress();
        AddressLivraison addressLivraison02 = purchase.getBillingAddress();
        if (addressLivraison != addressLivraison02) {
            commande.setShippingAddress(addressLivraison);
            addressLivraison.setActif(true);
            addressLivraison.setIsBillingAddress(0);
            commande.setBillingAddress(addressLivraison02);
            addressLivraison02.setActif(true);
            addressLivraison02.setIsBillingAddress(1);
        }else {
            commande.setShippingAddress(addressLivraison);
            addressLivraison.setActif(true);
            addressLivraison.setIsBillingAddress(0);
            commande.setBillingAddress(null);

        }
        Client client = purchase.getClient();
        client.setActif(true);
        client.add(commande);
        clientRepository.save(client);

        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }

    public long generateNumeroCommande() {
        final String FORMAT = "yyyyMMddHHmmss";
        return Long.parseLong(DateTimeFormat.forPattern(FORMAT).print(LocalDateTime.now()));
    }
}
package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.CheckoutApi;
import com.dp.dpshopbackend.dto.PurchaseDto;
import com.dp.dpshopbackend.dto.PurchaseResponse;
import com.dp.dpshopbackend.dto.UtilisateurDto;
import com.dp.dpshopbackend.dto.checkout.Purchase;
import com.dp.dpshopbackend.models.Commande;
import com.dp.dpshopbackend.models.Utilisateur;
import com.dp.dpshopbackend.services.CheckoutService;
import com.dp.dpshopbackend.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@CrossOrigin(origins = "https://soulbusinesse.com")
@RestController
public class CheckoutController implements CheckoutApi {

    private final CheckoutService checkoutService;

    private final UtilisateurService utilisateurService;

    @Autowired
    public CheckoutController(CheckoutService checkoutService,

                              UtilisateurService utilisateurService) {
        this.checkoutService = checkoutService;
        this.utilisateurService = utilisateurService;
    }

    @Override
    public ResponseEntity<PurchaseResponse> save(PurchaseDto purchaseDto) {
        return ResponseEntity.ok(checkoutService.placeOrder(purchaseDto));
    }

    @Override
    public ResponseEntity<PurchaseResponse> purchase(Purchase purchase) {
        PurchaseResponse response = this.checkoutService.placeToOrder(purchase);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PurchaseResponse> purchaseWithUser(Purchase purchase, Long id) {
        UtilisateurDto utilisateurDto = Optional.of(utilisateurService.findById(id)).get();
        Utilisateur utilisateur = UtilisateurDto.fromDtoToEntity(utilisateurDto);

        purchase.setUtilisateur(utilisateur);

        PurchaseResponse response = this.checkoutService.placeToOrderWithUser(purchase);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PurchaseResponse> purchaseWithLoginUser(Purchase purchase, Long id) {

        UtilisateurDto utilisateurDto = Optional.of(utilisateurService.findById(id)).get();

        Utilisateur utilisateur = UtilisateurDto.fromDtoToEntity(utilisateurDto);

        purchase.setUtilisateur(utilisateur);

        PurchaseResponse response = this.checkoutService.placeToOrderWithUser(purchase);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}

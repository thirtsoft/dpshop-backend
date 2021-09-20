package com.dp.dpshopbackend.controller;

import com.dp.dpshopbackend.controller.api.CheckoutApi;
import com.dp.dpshopbackend.dto.PurchaseDto;
import com.dp.dpshopbackend.dto.PurchaseResponse;
import com.dp.dpshopbackend.dto.checkout.Purchase;
import com.dp.dpshopbackend.services.CheckoutService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@AllArgsConstructor
public class CheckoutController implements CheckoutApi {

    private final CheckoutService checkoutService;

    @Override
    public ResponseEntity<PurchaseResponse> save(PurchaseDto purchaseDto) {
        return ResponseEntity.ok(checkoutService.placeOrder(purchaseDto));
    }

    @Override
    public ResponseEntity<PurchaseResponse> purchase(Purchase purchase) {
        PurchaseResponse response = this.checkoutService.placeToOrder(purchase);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

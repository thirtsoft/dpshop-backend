package com.dp.dpshopbackend.controller.api;

import com.dp.dpshopbackend.dto.PurchaseDto;
import com.dp.dpshopbackend.dto.PurchaseResponse;
import com.dp.dpshopbackend.dto.checkout.Purchase;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.dp.dpshopbackend.utils.Constants.APP_ROOT;

@RequestMapping(value = APP_ROOT + "/checkout")
public interface CheckoutApi {

    @PostMapping(value = "/purchase",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PurchaseResponse> save(@RequestBody PurchaseDto purchaseDto);

    @PostMapping(value = "/place-to-order",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PurchaseResponse> purchase(@RequestBody Purchase purchase);

    @PostMapping(value = "/place-to-order-with-user",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PurchaseResponse> purchaseWithUser(@RequestBody Purchase purchase, @RequestParam Long id);


    @PostMapping(value = "/place-to-order-with-login-user",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PurchaseResponse> purchaseWithLoginUser(@RequestBody Purchase purchase, @RequestParam  Long id);



}

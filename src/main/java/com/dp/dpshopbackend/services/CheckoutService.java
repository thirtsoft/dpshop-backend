package com.dp.dpshopbackend.services;

import com.dp.dpshopbackend.dto.PurchaseDto;
import com.dp.dpshopbackend.dto.PurchaseResponse;
import com.dp.dpshopbackend.dto.checkout.Purchase;

public interface CheckoutService {

    PurchaseResponse placeOrder(PurchaseDto purchaseDto);

    PurchaseResponse placeToOrder(Purchase purchase);
}

package com.dp.dpshopbackend.dto.checkout;

import com.dp.dpshopbackend.models.AddressLivraison;
import com.dp.dpshopbackend.models.Client;
import com.dp.dpshopbackend.models.Commande;
import com.dp.dpshopbackend.models.LigneCommande;
import lombok.Data;

import java.util.List;

@Data
public class Purchase {
    private Client client;
    private AddressLivraison shippingAddress;
    private AddressLivraison billingAddress;
    private Commande commande;
    private List<LigneCommande> lcomms;

}

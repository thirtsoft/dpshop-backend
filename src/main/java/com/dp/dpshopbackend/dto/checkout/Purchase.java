package com.dp.dpshopbackend.dto.checkout;

import com.dp.dpshopbackend.models.*;
import lombok.Data;

import java.util.List;

@Data
public class Purchase {
    private Client client;
    private Utilisateur utilisateur;
    private AddressLivraison shippingAddress;
    private AddressLivraison billingAddress;
    private Commande commande;
    private List<LigneCommande> lcomms;

}

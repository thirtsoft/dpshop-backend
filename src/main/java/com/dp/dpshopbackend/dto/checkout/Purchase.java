package com.dp.dpshopbackend.dto.checkout;

import com.dp.dpshopbackend.models.*;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class Purchase {
    @NotNull
    private Client client;
    @NotNull
    private Utilisateur utilisateur;
    @NotNull
    private AddressLivraison shippingAddress;
    @NotNull
    private AddressLivraison billingAddress;
    @NotNull
    private Commande commande;
    @NotNull
    private List<LigneCommande> lcomms;

}

package com.dp.dpshopbackend.dto;

import com.dp.dpshopbackend.models.Commande;
import com.dp.dpshopbackend.models.Utilisateur;

import javax.validation.constraints.NotNull;

public class PlaceOrderDto {
    private Long id;
    @NotNull
    private Utilisateur utilisateur;
    @NotNull
    private Double totalPrice;

    public PlaceOrderDto() {
    }

    public PlaceOrderDto(Commande commande) {
        this.setId(commande.getId());
        this.setTotalPrice(commande.getTotalCommande());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

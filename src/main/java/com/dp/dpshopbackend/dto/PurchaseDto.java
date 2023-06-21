package com.dp.dpshopbackend.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class PurchaseDto {

    @NotNull(message = "Le client ne doit pas etre vide")
    @NotEmpty(message = "Le client ne doit pas etre vide")
    @NotBlank(message = "Le client ne doit pas etre vide")
    private ClientDto clientDto;

    @NotNull(message = "L'utilisateur ne doit pas etre vide")
    @NotEmpty(message = "L'utilisateur ne doit pas etre vide")
    @NotBlank(message = "L'utilisateur ne doit pas etre vide")
    private UtilisateurDto utilisateurDto;

    @NotNull(message = "L'addresse de livraison ne doit pas etre vide")
    @NotEmpty(message = "L'addresse de livraison ne doit pas etre vide")
    @NotBlank(message = "L'addresse de livraison ne doit pas etre vide")
    private AddressLivraisonDto shippingAddressDto;

    @NotNull(message = "L'addresse de livraison ne doit pas etre vide")
    @NotEmpty(message = "L'addresse de livraison ne doit pas etre vide")
    @NotBlank(message = "L'addresse de livraison ne doit pas etre vide")
    private AddressLivraisonDto billingAddressDto;

    @NotNull(message = "La commande ne doit pas etre vide")
    @NotEmpty(message = "La commande ne doit pas etre vide")
    @NotBlank(message = "La commande ne doit pas etre vide")
    private CommandeDto commandeDto;

    @NotNull(message = "La ligne de commande ne doit pas etre vide")
    @NotEmpty(message = "La ligne de commande ne doit pas etre vide")
    @NotBlank(message = "La ligne de commande ne doit pas etre vide")
    private List<LigneCommandeDto> ligneCommandeListDtos;



  /*  private ClientDto clientDto;
    private AddressLivraisonDto shAddressLivraisonDto;
    private AddressLivraisonDto biAddressLivraisonDto;
    private CommandeDto commandeDto;
    private List<LigneCommandeDto> ligneCommandeDtoList;*/
}


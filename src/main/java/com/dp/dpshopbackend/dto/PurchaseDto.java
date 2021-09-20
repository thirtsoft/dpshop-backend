package com.dp.dpshopbackend.dto;

import com.dp.dpshopbackend.models.AddressLivraison;
import lombok.Data;

import java.util.List;

@Data
public class PurchaseDto {
    private ClientDto clientDto;
    private AddressLivraisonDto shippingAddressDto;
    private AddressLivraisonDto billingAddressDto;
    private CommandeDto commandeDto;
    private List<LigneCommandeDto> ligneCommandeListDtos;



  /*  private ClientDto clientDto;
    private AddressLivraisonDto shAddressLivraisonDto;
    private AddressLivraisonDto biAddressLivraisonDto;
    private CommandeDto commandeDto;
    private List<LigneCommandeDto> ligneCommandeDtoList;*/
}


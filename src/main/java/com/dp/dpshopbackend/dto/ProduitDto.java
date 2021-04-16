package com.dp.dpshopbackend.dto;

import com.dp.dpshopbackend.models.Produit;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProduitDto {

    private String reference;

    private String designation;

    private int quantity;

    private double price;

    private double currentPrice;

    private boolean promo;

    private boolean selected;

    private String description;

    private String photo;

    private ScategorieDto scategorieDto;

    public static ProduitDto fromEntityToDto(Produit produit) {
        if (produit == null) {
            return null;
        }

        return ProduitDto.builder()
                .reference(produit.getReference())
                .designation(produit.getDesignation())
                .quantity(produit.getQuantity())
                .price(produit.getPrice())
                .currentPrice(produit.getCurrentPrice())
                .promo(produit.isPromo())
                .selected(produit.isSelected())
                .description(produit.getDescription())
                .photo(produit.getPhoto())
                .scategorieDto(ScategorieDto.fromEntityToDto(produit.getScategorie()))
                .build();
    }

    public static Produit fromDtoToEntity(ProduitDto produitDto) {
        if (produitDto == null) {
            return null;
        }

        Produit produit = new Produit();
        produit.setReference(produitDto.getReference());
        produit.setDesignation(produitDto.getDesignation());
        produit.setQuantity(produitDto.getQuantity());
        produit.setPrice(produitDto.getPrice());
        produit.setCurrentPrice(produitDto.getCurrentPrice());
        produit.setPromo(produitDto.isPromo());
        produit.setSelected(produitDto.isSelected());
        produit.setDescription(produitDto.getDescription());
        produit.setPhoto(produitDto.getPhoto());

        return produit;
    }

}

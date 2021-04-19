package com.dp.dpshopbackend.dto;

import com.dp.dpshopbackend.models.LigneCommande;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LigneCommandeDto {

    private long id;

    private long numero;

    private int quantity;

    private double price;

    private CommandeDto commandeDto;

    private ArticleDto articleDto;

    public static LigneCommandeDto fromEntityToDto(LigneCommande ligneCommande) {
        if (ligneCommande == null) {
            return null;
        }

        return LigneCommandeDto.builder()
                .id(ligneCommande.getId())
                .numero(ligneCommande.getNumero())
                .quantity(ligneCommande.getQuantity())
                .price(ligneCommande.getPrice())
                .commandeDto(CommandeDto.fromEntityToDto(ligneCommande.getCommande()))
                .articleDto(ArticleDto.fromEntityToDto(ligneCommande.getArticle()))
                .build();
    }

    public static LigneCommande fromDtoToEntity(LigneCommandeDto ligneCommandeDto) {
        if (ligneCommandeDto == null) {
            return null;
        }

        LigneCommande ligneCommande = new LigneCommande();
        ligneCommande.setId(ligneCommandeDto.getId());
        ligneCommande.setNumero(ligneCommandeDto.getNumero());
        ligneCommande.setQuantity(ligneCommandeDto.getQuantity());
        ligneCommande.setPrice(ligneCommandeDto.getPrice());

        return ligneCommande;
    }

}

package com.dp.dpshopbackend.dto;

import com.dp.dpshopbackend.enumeration.StatusCommande;
import com.dp.dpshopbackend.models.Commande;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class CommandeDto {

    private String reference;

    private String numeroCommande;

    private double total;

    private LocalDateTime localDateTime;

    private StatusCommande statusCommande;

    private ClientDto clientDto;

    private List<LigneCommandeDto> lcomms = new ArrayList<>();

    public static CommandeDto formEntityToDto(Commande commande) {
        if (commande == null) {
            return null;
        }

        return CommandeDto.builder()
                .reference(commande.getReference())
                .numeroCommande(commande.getNumeroCommande())
                .total(commande.getTotal())
                .localDateTime(commande.getLocalDateTime())
                .statusCommande(commande.getStatusCommande())
                .clientDto(ClientDto.fromEntityToDto(commande.getClient()))
                .build();

    }

    public static Commande fromDtoToEntity(CommandeDto commandeDto) {
        if (commandeDto == null) {
            return null;
        }

        Commande commande = new Commande();
        commande.setReference(commandeDto.getReference());
        commande.setNumeroCommande(commandeDto.getNumeroCommande());
        commande.setTotal(commandeDto.getTotal());
        commande.setLocalDateTime(commandeDto.getLocalDateTime());
        commande.setStatusCommande(commandeDto.getStatusCommande());

        return commande;
    }

}

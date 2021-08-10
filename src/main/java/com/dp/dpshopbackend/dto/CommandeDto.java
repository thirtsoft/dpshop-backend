package com.dp.dpshopbackend.dto;

import com.dp.dpshopbackend.enumeration.StatusCommande;
import com.dp.dpshopbackend.models.Commande;
import com.dp.dpshopbackend.models.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommandeDto {

    private Long id;

    private String reference;

    private Long numeroCommande;

    private double total;

    private LocalDateTime localDateTime;

    private Date dateCommande;

    private StatusCommande statusCommande;

  //  private ClientDto clientDto;

    private UtilisateurPOSTDto utilisateurPOSTDto;

    private AddressLivraisonDto addressLivraisonDto;

    //  @JsonIgnore
    //   private List<LigneCommandeDto> lcomms;
    private List<LigneCommandeDto> lcomms = new ArrayList<>();

    public static CommandeDto fromEntityToDto(Commande commande) {
        if (commande == null) {
            return null;
        }

        return CommandeDto.builder()
                .id(commande.getId())
                .reference(commande.getReference())
                .numeroCommande(commande.getNumeroCommande())
                .total(commande.getTotal())
                .localDateTime(commande.getLocalDateTime())
                .dateCommande(commande.getDateCommande())
                .statusCommande(commande.getStatusCommande())
                .utilisateurPOSTDto(UtilisateurPOSTDto.fromEntityToDto(commande.getUtilisateur()))
                .addressLivraisonDto(AddressLivraisonDto.fromEntityToDto(commande.getAddressLivraison()))
                //         .lcomms((List<LigneCommandeDto>) LigneCommandeDto.fromEntityToDto((LigneCommande) commande.getLcomms()))
                .build();

    }

    public static Commande fromDtoToEntity(CommandeDto commandeDto) {
        if (commandeDto == null) {
            return null;
        }

        Commande commande = new Commande();
        commande.setId(commandeDto.getId());
        commande.setReference(commandeDto.getReference());
        commande.setNumeroCommande(commandeDto.getNumeroCommande());
        commande.setDateCommande(commandeDto.getDateCommande());
        commande.setTotal(commandeDto.getTotal());
        commande.setUtilisateur(UtilisateurPOSTDto.fromDtoToEntity(commandeDto.getUtilisateurPOSTDto()));
        commande.setAddressLivraison(AddressLivraisonDto.fromDtoToEntity(commandeDto.getAddressLivraisonDto()));
        //   commande.setLcomms((List<LigneCommande>) LigneCommandeDto.fromDtoToEntity((LigneCommandeDto) commandeDto.getLcomms()));
        commande.setLocalDateTime(commandeDto.getLocalDateTime());
        commande.setStatusCommande(commandeDto.getStatusCommande());

        return commande;
    }

}

package com.dp.dpshopbackend.dto;

import com.dp.dpshopbackend.models.Commande;
import com.dp.dpshopbackend.models.LigneCommande;
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

    private Long numeroCommande;

    private double total;

    private String orderTrackingNumber;

    private LocalDateTime localDateTime;

    private Date dateCommande;

    private String status;

    private ClientDto clientDto;

    private UtilisateurDto utilisateurDto;

    private AddressLivraisonDto shippingAddressDto;

    private AddressLivraisonDto billingAddressDto;

    //  @JsonIgnore
    //   private List<LigneCommandeDto> lcomms;
    //   private List<LigneCommandeDto> lcomms = new ArrayList<>();

    private List<LigneCommande> lcomms = new ArrayList<>();

    public static CommandeDto fromEntityToDto(Commande commande) {
        if (commande == null) {
            return null;
        }

        return CommandeDto.builder()
                .id(commande.getId())
                .numeroCommande(commande.getNumeroCommande())
                .total(commande.getTotalCommande())
                .localDateTime(commande.getLocalDateTime())
                .dateCommande(commande.getDateCommande())
                .status(commande.getStatus())
                .orderTrackingNumber(commande.getOrderTrackingNumber())
                .clientDto(ClientDto.fromEntityToDto(commande.getClient()))
                .utilisateurDto(UtilisateurDto.fromEntityToDto(commande.getUtilisateur()))
                .billingAddressDto(AddressLivraisonDto.fromEntityToDto(commande.getBillingAddress()))
                .shippingAddressDto(AddressLivraisonDto.fromEntityToDto(commande.getShippingAddress()))
                .lcomms(commande.getLcomms())
                .build();

    }

    public static Commande fromDtoToEntity(CommandeDto commandeDto) {
        if (commandeDto == null) {
            return null;
        }

        Commande commande = new Commande();
        commande.setId(commandeDto.getId());
        commande.setNumeroCommande(commandeDto.getNumeroCommande());
        commande.setDateCommande(commandeDto.getDateCommande());
        commande.setOrderTrackingNumber(commandeDto.getOrderTrackingNumber());
        commande.setTotalCommande(commandeDto.getTotal());
        commande.setClient(ClientDto.fromDtoToEntity(commandeDto.getClientDto()));
        commande.setUtilisateur(UtilisateurDto.fromDtoToEntity(commandeDto.getUtilisateurDto()));
        commande.setBillingAddress(AddressLivraisonDto.fromDtoToEntity(commandeDto.getBillingAddressDto()));
        commande.setShippingAddress(AddressLivraisonDto.fromDtoToEntity(commandeDto.getShippingAddressDto()));
        commande.setLcomms(commandeDto.getLcomms());
        commande.setLocalDateTime(commandeDto.getLocalDateTime());
        commande.setStatus(commandeDto.getStatus());

        return commande;
    }

  /*  public void add(LigneCommande ligneCommande) {
        if (ligneCommande != null) {
            if (lcomms == null) {
                lcomms = new ArrayList<>();
            }
            lcomms.add(ligneCommande);
            ligneCommande.setCommandeDto(this);
        }
    }*/

    /*public void add(LigneCommandeDto ligneCommandeDto) {
        if (ligneCommandeDto != null) {
            if (lcomms == null) {
                lcomms = new ArrayList<>();
            }
            lcomms.add(ligneCommandeDto);
            ligneCommandeDto.setCommandeDto(this);
        }
    }*/

}

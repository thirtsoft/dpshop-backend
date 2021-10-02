package com.dp.dpshopbackend.dto;

import com.dp.dpshopbackend.enumeration.StatusCommande;
import com.dp.dpshopbackend.models.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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

    private String orderTrackingNumber;

    private LocalDateTime localDateTime;

    private Date dateCommande;

    private StatusCommande statusCommande;

    private ClientDto clientDto;

    private AddressLivraisonDto shippingAddressDto;

    private AddressLivraisonDto billingAddressDto;

//    private AddressLivraisonDto addressLivraisonDto;

    //  @JsonIgnore
    //   private List<LigneCommandeDto> lcomms;
    private List<LigneCommandeDto> lcomms = new ArrayList<>();

    public void add(LigneCommandeDto ligneCommandeDto) {
        if (ligneCommandeDto != null) {
            if (lcomms == null) {
                lcomms = new ArrayList<>();
            }
            lcomms.add(ligneCommandeDto);
            ligneCommandeDto.setCommandeDto(this);
        }
    }

    public static CommandeDto fromEntityToDto(Commande commande) {
        if (commande == null) {
            return null;
        }

        return CommandeDto.builder()
                .id(commande.getId())
                .reference(commande.getReference())
                .numeroCommande(commande.getNumeroCommande())
                .total(commande.getTotalCommande())
                .localDateTime(commande.getLocalDateTime())
                .dateCommande(commande.getDateCommande())
                .statusCommande(commande.getStatusCommande())
                .orderTrackingNumber(commande.getOrderTrackingNumber())
                .clientDto(ClientDto.fromEntityToDto(commande.getClient()))
                .billingAddressDto(AddressLivraisonDto.fromEntityToDto(commande.getBillingAddress()))
                .shippingAddressDto(AddressLivraisonDto.fromEntityToDto(commande.getShippingAddress()))

             /*   .addressLivraisonDto(AddressLivraisonDto.fromEntityToDto(commande.getAddressLivraison()))*/
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
        commande.setOrderTrackingNumber(commandeDto.getOrderTrackingNumber());
        commande.setTotalCommande(commandeDto.getTotal());
        commande.setClient(ClientDto.fromDtoToEntity(commandeDto.getClientDto()));
        commande.setBillingAddress(AddressLivraisonDto.fromDtoToEntity(commandeDto.getBillingAddressDto()));
        commande.setShippingAddress(AddressLivraisonDto.fromDtoToEntity(commandeDto.getShippingAddressDto()));
      /*  commande.setAddressLivraison(AddressLivraisonDto.fromDtoToEntity(commandeDto.getAddressLivraisonDto()));*/
        //   commande.setLcomms((List<LigneCommande>) LigneCommandeDto.fromDtoToEntity((LigneCommandeDto) commandeDto.getLcomms()));
        commande.setLocalDateTime(commandeDto.getLocalDateTime());
        commande.setStatusCommande(commandeDto.getStatusCommande());

        return commande;
    }

}

package com.dp.dpshopbackend.models;

import com.dp.dpshopbackend.dto.PlaceOrderDto;
import com.dp.dpshopbackend.enumeration.StatusCommande;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "commande")
@Data
public class Commande implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference", length = 70)
    private String reference;

    @Column(name = "numeroCommande", length = 70)
    private Long numeroCommande;

    @Column(name = "totalQuantity", length = 150)
    private int totalQuantity;

    @Column(name = "totalCommande", length = 150)
    private double totalCommande;

    @Column(name = "localDateTime", length = 100)
    private LocalDateTime localDateTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT")
    private Date dateCommande;

    @Column(name = "created_date")
    private Date createdDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 100)
    private StatusCommande statusCommande;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "orderTrackingNumber")
    private String orderTrackingNumber;

    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;

  /*  @ManyToOne
    @JoinColumn(name = "userId")
    private Utilisateur utilisateur;*/

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_address_id", referencedColumnName = "id")
    private AddressLivraison shippingAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_address_id", referencedColumnName = "id")
    private AddressLivraison billingAddress;

  /*  @ManyToOne
    @JoinColumn(name = "userId")
    private Utilisateur utilisateur;*/

    /*
    @OneToMany(mappedBy = "commande", fetch = FetchType.LAZY)
    private List<LigneCommande> lcomms = new ArrayList<>();*/

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commande", fetch = FetchType.LAZY)
    private List<LigneCommande> lcomms = new ArrayList<>();

   /* @ManyToOne
    @JoinColumn(name = "billingAddress")
    private AddressLivraison addressLivraison;
*/

    public Commande() {
    }


    public Commande(PlaceOrderDto placeOrderDto, String sessionId) {
        this.createdDate = new Date();
        this.totalCommande = placeOrderDto.getTotalPrice();
        this.sessionId = sessionId;
    }

    public void add(LigneCommande ligneCommande) {
        if (ligneCommande != null) {
            if (lcomms == null) {
                lcomms = new ArrayList<>();
            }
            lcomms.add(ligneCommande);
            ligneCommande.setCommande(this);
        }
    }


}

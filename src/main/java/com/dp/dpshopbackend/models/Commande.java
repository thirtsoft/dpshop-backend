package com.dp.dpshopbackend.models;

import com.dp.dpshopbackend.enumeration.StatusCommande;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "commande")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Commande implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference", length = 70)
    private String reference;

    @Column(name = "numeroCommande", length = 70)
    private Long numeroCommande;

    @Column(name = "totalCommande", length = 150)
    private double total;

    @Column(name = "dateCommande", length = 100)
    private LocalDateTime localDateTime;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 100)
    private StatusCommande statusCommande;

    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;

    @OneToMany(mappedBy = "commande")
    private List<LigneCommande> lcomms = new ArrayList<>();

    public Commande() { }

    public Commande(Long id, Long numeroCommande, LocalDateTime localDateTime, Client client, List<LigneCommande> lcomms, double total, StatusCommande statusCommande) {
        this.id = id;
        this.numeroCommande = numeroCommande;
        this.localDateTime = localDateTime;
        this.client = client;
        this.lcomms = lcomms;
        this.total = total;
        this.statusCommande = statusCommande;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Long getNumeroCommande() {
        return numeroCommande;
    }

    public void setNumeroCommande(Long numeroCommande) {
        this.numeroCommande = numeroCommande;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public StatusCommande getStatusCommande() {
        return statusCommande;
    }

    public void setStatusCommande(StatusCommande statusCommande) {
        this.statusCommande = statusCommande;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<LigneCommande> getLcomms() {
        return lcomms;
    }

    public void setLcomms(List<LigneCommande> lcomms) {
        this.lcomms = lcomms;
    }
}

package com.dp.dpshopbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ligneCommande")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class LigneCommande implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero", length = 90)
    private Long numero;

    @Column(name = "quantity", length = 70)
    private int quantity;

    @Column(name = "price", length = 70)
    private double price;
/*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comId")
    @JsonIgnoreProperties(value = {"lcomms"})
    private Commande commande;
    */


    @ManyToOne
    @JoinColumn(name = "comId")
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "prodId")
    private Article article;
}

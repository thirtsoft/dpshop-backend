package com.dp.dpshopbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ligneCommande")
@Data@AllArgsConstructor
@NoArgsConstructor
public class LigneCommande extends AbstractEntity {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/

    @Column(name = "numero", length = 90)
    private long numero;

    @Column(name = "quantity", length = 70)
    private int quantity;

    @Column(name = "price", length = 70)
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comId")
    @JsonIgnoreProperties(value = {"lcomms"})
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "prodId")
    private Article article;
}

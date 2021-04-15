package com.dp.dpshopbackend.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "notation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification extends AbstractEntity {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/

    @Column(name = "reference", length = 60)
    private String reference;

    @Column(name = "nbreEtoile", length = 60)
    private String nbreEtoile;

    @Column(name = "observation", length = 200)
    private String observation;

    @ManyToOne
    @JoinColumn(name = "prodId", nullable = false)
    private Produit produit;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private Utilisateur utilisateur;

}

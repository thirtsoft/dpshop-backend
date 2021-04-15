package com.dp.dpshopbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "fournisseur")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fournisseur extends AbstractEntity {

   /* @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/

    @Column(name = "reference", length = 50)
    private String reference;

    @Column(name = "firstName", length = 90)
    private String firstName;

    @Column(name = "lastName", length = 70)
    private String lastName;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "telephoneFournisseur", length = 50)
    private String telephoneFournisseur;

    @Column(name = "villeFournisseur", length = 100)
    private String city;

    @Column(name = "paysFournisseur", length = 100)
    private String country;

    @ManyToOne
    @JoinColumn(name = "prodId")
    private Produit produit;


}

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
public class Fournisseur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    private String subject;

    private String message;

    @ManyToOne
    @JoinColumn(name = "artId")
    private Article article;

    @Column(name = "actif")
    private int actif;

    public void setActif(boolean actif) {
        if (actif == true)
            this.actif = 1;
        else
            this.actif = 0;
    }

    public boolean isActif() {
        if (actif == 1)
            return true;
        else
            return false;
    }

    public Fournisseur(Long id, String reference, String firstName, String lastName, String address, String email, String telephoneFournisseur,
                       String city, String country, Article article) {
        this.id = id;
        this.reference = reference;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.telephoneFournisseur = telephoneFournisseur;
        this.city = city;
        this.country = country;
        this.article = article;
    }
}

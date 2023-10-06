package com.dp.dpshopbackend.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "notation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nbreEtoile", length = 60)
    private float nbreEtoile;

    @Column(name = "observation")
    @Lob
    private String observation;

    @Column(name = "createdDate")
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "artId")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Utilisateur utilisateur;

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

    public Notification(Long id, float nbEtoile, String observation, Article article) {
        this.id = id;
        this.nbreEtoile = nbEtoile;
        this.observation = observation;
        this.createdDate = new Date();
        this.article = article;
    }

}

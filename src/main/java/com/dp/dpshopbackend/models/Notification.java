package com.dp.dpshopbackend.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "notation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference", length = 60)
    private String reference;

    @Column(name = "nbreEtoile", length = 60)
    private String nbreEtoile;

    @Column(name = "observation", length = 200)
    private String observation;

    @ManyToOne
    @JoinColumn(name = "prodId")
    private Article article;

    /*
    @ManyToOne
    @JoinColumn(name = "userId")
    private Utilisateur utilisateur;
    */

}

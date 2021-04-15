package com.dp.dpshopbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "scategorie")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Scategorie extends AbstractEntity {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "libelle", length = 90)
    private String libelle;

    @ManyToOne
    @JoinColumn(name = "catId", nullable = false)
    private Categorie categorie;
}

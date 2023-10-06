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
public class Scategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "libelle", length = 90)
    private String libelle;

    @ManyToOne
    @JoinColumn(name = "catId")
    private Category category;

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

    public Scategory(Long id, String code, String libelle, Category category) {
        this.id = id;
        this.code = code;
        this.libelle = libelle;
        this.category = category;
    }
}

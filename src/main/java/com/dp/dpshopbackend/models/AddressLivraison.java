package com.dp.dpshopbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "addressLivraison")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressLivraison implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference", length = 50)
    private String reference;

    @Column(name = "zipcode", length = 90)
    private String zipcode;

    @Column(name = "phone", length = 63)
    private String phone;

    @Column(name = "ville", length = 70)
    private String city;

    @Column(name = "rue", length = 90)
    private String rue;

    @Column(name = "pays", length = 70)
    private String country;

    @ManyToOne
    @JoinColumn(name = "stateId")
    // @JsonIgnore
    private State state;

    /*
    @OneToOne
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private Commande commande;*/

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

    public AddressLivraison(Long id, String reference, String zipcode,
                            String phone, String city, String rue, String country,
                            State state) {
        this.id = id;
        this.reference = reference;
        this.zipcode = zipcode;
        this.phone = phone;
        this.city = city;
        this.rue = rue;
        this.country = country;
        this.state = state;
    }
}

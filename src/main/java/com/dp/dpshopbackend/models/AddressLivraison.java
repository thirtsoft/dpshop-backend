package com.dp.dpshopbackend.models;

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

    @Column(name = "quartier", length = 90)
    private String quartier;

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
    private State state;
}

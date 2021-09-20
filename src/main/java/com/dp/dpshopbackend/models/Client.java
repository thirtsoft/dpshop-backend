package com.dp.dpshopbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName", length = 90)
    private String firstName;

    @Column(name = "lastName", length = 70)
    private String lastName;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "mobile", length = 30)
    private String mobile;
/*
    @OneToMany(mappedBy = "client")
    private List<Commande> commandeList;
    */

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Commande> commandeList = new ArrayList<>();

    public Client(Long id, String firstName,
                  String lastName, String mobile, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
    }

    public void add(Commande commande) {
        if (commande != null) {
            if (commandeList == null) {
                commandeList = new ArrayList<>();
            }
            commandeList.add(commande);
            commande.setClient(this);
        }
    }

}

package com.dp.dpshopbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @Column(name = "firstName", length = 90,nullable = false)
    @NotNull
    private String firstName;

    @Column(name = "lastName", length = 70,nullable = false)
    @NotNull
    private String lastName;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "username", length = 30)
    private String username;

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "password", length = 30)
    private String password;

    @Column(name = "mobile", length = 30, nullable = false)
    @NotNull
    private String mobile;


  /*  @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private Utilisateur utilisateur;*/


    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
  //  @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Commande> commandeList = new ArrayList<>();

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

    public Client(String firstName, String lastName, String mobile,
                  String email,
                  String username,
                  String password,
                  String name) {
        Utilisateur utilisateur = new Utilisateur();
        //    this.id = this.getId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;


    }

    public Client(String firstName, String lastName, String email, String mobile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;

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

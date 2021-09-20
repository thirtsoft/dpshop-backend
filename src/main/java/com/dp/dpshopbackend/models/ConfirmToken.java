package com.dp.dpshopbackend.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "confirmToken")
public class ConfirmToken implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token", length = 90)
    private String token;

    @Column(name = "created_date")
    private Date createdDate;

    @OneToOne(targetEntity = Utilisateur.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "userId")
    private Utilisateur utilisateur;

    public ConfirmToken() {
    }


    public ConfirmToken(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        this.createdDate = new Date();
        this.token = UUID.randomUUID().toString();
    }

    public ConfirmToken(Long id, String Token, Date createdDate, Utilisateur utilisateur) {
        this.id = id;
        this.token = Token;
        this.createdDate = createdDate;
        this.utilisateur = utilisateur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
}

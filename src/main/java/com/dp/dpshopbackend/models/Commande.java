package com.dp.dpshopbackend.models;

import com.dp.dpshopbackend.enumeration.StatusCommande;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode()
@Entity
@Table(name = "commande")
public class Commande implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference", length = 70)
    private String reference;

    @Column(name = "numeroCommande", length = 70)
    private Long numeroCommande;

    @Column(name = "totalCommande", length = 150)
    private double total;

    @Column(name = "dateCommande", length = 100)
    private LocalDateTime localDateTime;

    //  @Enumerated(EnumType.STRING)
    //  @NaturalId
    @Column(length = 100)
    private StatusCommande statusCommande;

    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;

    @OneToMany(mappedBy = "commande")
    private List<LigneCommande> lcomms = new ArrayList<>();


}

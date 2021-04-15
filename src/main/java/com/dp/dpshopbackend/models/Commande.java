package com.dp.dpshopbackend.models;

import com.dp.dpshopbackend.enumeration.StatusCommande;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "commande")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commande extends AbstractEntity {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/

    @Column(name = "reference", length = 70)
    private String reference;

    @Column(name = "numeroCommande", length = 70)
    private String numeroCommande;

    @Column(name = "totalCommande", length = 150)
    private double total;

    @Column(name = "dateCommande", length = 100)
    private LocalDateTime localDateTime;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 100)
    private StatusCommande statusCommande;

    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;

    @OneToMany(mappedBy = "commande", fetch = FetchType.LAZY)
    // @Valid
    private List<LigneCommande> lcomms = new ArrayList<>();
}

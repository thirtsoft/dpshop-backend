package com.dp.dpshopbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "article")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference", length = 70)
    private String reference;

    @Column(name = "designation", length = 100)
    private String designation;

    @Column(name = "quantity", length = 50)
    private int quantity;

    @Transient
    private int quantite = 1;

    @Column(name = "price", length = 50)
    private double price;

    @Column(name = "currentPrice", length = 50)
    private double currentPrice;

    private boolean promo;

    private boolean selected;

    @Column(name = "description", length = 250)
    private String description;

    @Column(name = "photo")
    private String photo;

    @ManyToOne
    @JoinColumn(name = "scatId")
    private Scategory scategory;

    public Article(Long id, String reference, String designation, int quantity,
                   double price, double currentPrice, boolean promo, boolean selected,
                   String description, String photo, Scategory scategory) {
        this.id = id;
        this.reference = reference;
        this.designation = designation;
        this.quantity = quantity;
        this.price = price;
        this.currentPrice = currentPrice;
        this.promo = promo;
        this.selected = selected;
        this.description = description;
        this.photo = photo;
        this.scategory = scategory;
    }
}

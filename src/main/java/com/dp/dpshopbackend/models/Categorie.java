package com.dp.dpshopbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "categorie")
@Data
//@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Categorie extends AbstractEntity {

  /*  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "designation", length = 90)
    private String designation;
}

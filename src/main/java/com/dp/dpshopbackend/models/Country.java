package com.dp.dpshopbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "country")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", length = 30)
    private String code;

    @Column(name = "name", length = 90)
    private String name;

    @OneToMany(mappedBy = "country")
    @JsonIgnore
    private List<State> stateList;

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

    public Country(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Country(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }
}

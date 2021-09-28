package com.dp.dpshopbackend.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "utilisateur", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class Utilisateur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "username", length = 60)
    private String username;

    @Column(name = "mobile", length = 60)
    private String mobile;

    @Column(name = "email", length = 50)
    private String email;

  /*
    @Column(name = "firstName", length = 100)
    private String firstName;

    @Column(name = "lastName", length = 100)
    private String lastName;*/

    @Column(name = "password", length = 80)
    private String password;


    @Column(name = "activated")
    private boolean activated = false;

    @Column(name = "accountVerified")
    private boolean accountVerified;

  /*  @Enumerated(EnumType.STRING)
    @Column(name = "roleName")
    private RoleName roleName;*/

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    /* @JsonIgnore
     @OneToMany(mappedBy = "utilisateur",
             fetch = FetchType.LAZY)
     private List<Commande> commandeList;
 */
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private List<Commande> commandeList = new ArrayList<>();

    public Utilisateur() {
    }

    public Utilisateur(String username,
                       String email,
                       String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Utilisateur(String name,
                       String username,
                       String email,
                       String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Utilisateur(Long id, String name, String username, String mobile,
                       String email, String password, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public void add(Commande commande) {
        if (commande != null) {
            if (commandeList == null) {
                commandeList = new ArrayList<>();
            }
            commandeList.add(commande);
            commande.setUtilisateur(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAccountVerified() {
        return accountVerified;
    }

    public void setAccountVerified(boolean accountVerified) {
        this.accountVerified = accountVerified;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Commande> getCommandeList() {
        return commandeList;
    }

    public void setCommandeList(List<Commande> commandeList) {
        this.commandeList = commandeList;
    }
}

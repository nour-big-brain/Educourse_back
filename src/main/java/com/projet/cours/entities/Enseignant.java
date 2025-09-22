package com.projet.cours.entities;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.projet.cours.entities.Cours;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Enseignant {
    @Id
    @GeneratedValue
    private int idEns;
    private String nom_ens;
    private String emailEns;
    private String specialite;
    private String password;
    private String role = "TEACHER";

    @OneToMany(mappedBy = "enseignant")
    @JsonManagedReference
    private List<Cours> cours = new ArrayList<>();

    public int getIdEns() {
        return idEns;
    }

    public void setIdEns(int idEns) {
        this.idEns = idEns;
    }

    public String getNom_ens() {
        return nom_ens;
    }

    public void setNom_ens(String nom_ens) {
        this.nom_ens = nom_ens;
    }

    public String getEmailEns() {
        return emailEns;
    }

    public void setEmailEns(String emailEns) {
        this.emailEns = emailEns;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Cours> getCours() {
        return cours;
    }

    public void setCours(List<Cours> cours) {
        this.cours = cours;
    }
}

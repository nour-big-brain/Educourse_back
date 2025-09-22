package com.projet.cours.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Etudiant {

    @Id
    @GeneratedValue
    private int idEtud;
    private String nomEtud;
    private  String emailEtud;
    private String password;
    private Date dateNaissance;
    private String niveau;
    private String role = "STUDENT";
    @ManyToMany
    @JoinTable(
            name = "etudiant_cours",
            joinColumns = @JoinColumn(name = "etudiant_id"),
            inverseJoinColumns = @JoinColumn(name = "cours_id")
    )
    private List<Cours> cours;
    @ManyToMany
    @JoinTable(
            name = "etudiant_examen",
            joinColumns = @JoinColumn(name = "etudiant_id"),
            inverseJoinColumns = @JoinColumn(name = "examen_id")
    )
    private List<Examen> examens;
    @OneToMany(mappedBy = "etudiant")
    private List<Certificat> certificats;

    public int getIdEtud() {
        return idEtud;
    }


    public void setIdEtud(int idEtud) {
        this.idEtud = idEtud;
    }

    public String getNomEtud() {
        return nomEtud;
    }

    public void setNomEtud(String nomEtud) {
        this.nomEtud = nomEtud;
    }

    public String getEmailEtud() {
        return emailEtud;
    }

    public void setEmailEtud(String emailEtud) {
        this.emailEtud = emailEtud;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public List<Cours> getCours() {
        return cours;
    }

    public void setCours(List<Cours> cours) {
        this.cours = cours;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.projet.cours.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Certificat {
    @Id
    @GeneratedValue
    private int id_certif;
    private Date date_certif;
    private int note_finale;
    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "examen_id")
    private Examen examen;
    public int getId_certif() {
        return id_certif;
    }

    public void setId_certif(int id_certif) {
        this.id_certif = id_certif;
    }

    public Date getDate_certif() {
        return date_certif;
    }

    public void setDate_certif(Date date_certif) {
        this.date_certif = date_certif;
    }

    public int getNote_finale() {
        return note_finale;
    }

    public void setNote_finale(int note_finale) {
        this.note_finale = note_finale;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }
}

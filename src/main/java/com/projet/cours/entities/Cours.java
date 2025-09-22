package com.projet.cours.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
@Entity
public class Cours {
    @Id
    @GeneratedValue
    @Column(name = "id_cours")
    private int idCours;

    private String titre_cours;
    private int duree_cours;

    @Lob
    private String image;

    @ElementCollection
    private List<String> materials;

    @ManyToMany(mappedBy = "cours")
    private List<Etudiant> etudiants;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_enseignant")
    private Enseignant enseignant;

    @OneToMany(mappedBy = "cours")
    private List<Examen> examens;

    // Getters and Setters
    public int getIdCours() {
        return idCours;
    }

    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }

    public String getTitre_cours() {
        return titre_cours;
    }

    public void setTitre_cours(String titre_cours) {
        this.titre_cours = titre_cours;
    }

    public int getDuree_cours() {
        return duree_cours;
    }

    public void setDuree_cours(int duree_cours) {
        this.duree_cours = duree_cours;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getMaterials() {
        return materials;
    }

    public void setMaterials(List<String> materials) {
        this.materials = materials;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }
}
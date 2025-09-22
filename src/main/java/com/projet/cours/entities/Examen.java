    package com.projet.cours.entities;

    import jakarta.persistence.*;

    import java.util.Date;
    import java.util.List;

    @Entity
    public class Examen {
        @Id
        @GeneratedValue
        private int idEx;
        private int note;
        private Date date_ex;
        private String titre;

        @ManyToMany(mappedBy = "examens")
        private List<Etudiant> etudiants;
        @OneToMany(mappedBy = "examen")
        private List<Certificat> certificats;
        @ManyToOne
        @JoinColumn(name = "cours_id")
        private Cours cours;
        public int getIdEx() {
            return idEx;
        }

        public void setIdEx(int idEx) {
            this.idEx = idEx;
        }

        public String getTitre() {
            return titre;
        }

        public void setTitre(String titre) {
            this.titre = titre;
        }

        public int getNote() {
            return note;
        }

        public void setNote(int note) {
            this.note = note;
        }

        public Date getDate_ex() {
            return date_ex;
        }

        public void setDate_ex(Date date_ex) {
            this.date_ex = date_ex;
        }

        public List<Etudiant> getEtudiants() {
            return etudiants;
        }

        public void setEtudiants(List<Etudiant> etudiants) {
            this.etudiants = etudiants;
        }

        public List<Certificat> getCertificats() {
            return certificats;
        }

        public void setCertificats(List<Certificat> certificats) {
            this.certificats = certificats;
        }

        public Cours getCours() {
            return cours;
        }

        public void setCours(Cours cours) {
            this.cours = cours;
        }
    }

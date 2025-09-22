package com.projet.cours.services;

import com.projet.cours.entities.Cours;
import com.projet.cours.entities.Etudiant;

import java.util.List;

public interface IEtudiantService {
    public Etudiant saveEtudiant(Etudiant etudiant);
    public Etudiant updateEtudiant(Etudiant etudiant,int id);
    public void deleteEtudiant(int id);
    public Etudiant getEtudiantByid(int id);
    public List<Etudiant> getAllEtudiants();
    public List<Etudiant> getStudentsByCours(int coursId);
    public List<Cours> getEtudiantCours(int etudiantId);
    public void signUpForCourse(int etudiantId, int coursId);
    public List<String> getCoursMaterials(int coursId);

}

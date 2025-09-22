package com.projet.cours.services;

import com.projet.cours.entities.Cours;
import com.projet.cours.entities.Enseignant;

import java.util.List;

public interface IEnseignantService {
    public Enseignant saveEnseignant(Enseignant enseignant);
    public Enseignant updateEnseignant(int id,Enseignant enseignant);
    public Enseignant assignCoursToEnseignant(int enseignantId, int coursId);
    public List<Enseignant> getAllEnseignants();
    public List<Cours> getCoursesByEnseignant(int enseignantId);
    public void deleteEnseignant(int id);


}

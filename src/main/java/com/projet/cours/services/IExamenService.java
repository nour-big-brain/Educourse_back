package com.projet.cours.services;

import com.projet.cours.entities.Examen;

import java.util.Date;
import java.util.List;

public interface IExamenService {
    public Examen saveExamen(Examen examen);
    public Examen scheduleExamen(int id, Date date_ex);
    public Examen assignEtudiantToExamen(int idExam,int idEtud);
    public Double getStudentExamResult(int etudiantId, int examenId);
    public List<Examen> getAllExams();
    public void deleteExamen(int id);
}

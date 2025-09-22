package com.projet.cours.services;

import com.projet.cours.entities.Etudiant;
import com.projet.cours.entities.Examen;
import com.projet.cours.repositories.EtudiantRepository;
import com.projet.cours.repositories.ExamenRepository;
import com.projet.cours.repositories.QuestionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class ExamenServiceImpl implements IExamenService {
    @Autowired
    private ExamenRepository examenRepository;
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Examen saveExamen(Examen examen) {
        return examenRepository.save(examen);
    }

    @Override
    public Examen scheduleExamen(int id, Date date_ex) {
        Optional<Examen> e = examenRepository.findById(id);
        if (e.isPresent()) {
            Examen examen = e.get();
            examen.setDate_ex(date_ex);
            return examenRepository.save(examen);
        }
        return null;
    }

    @Override
    public Examen assignEtudiantToExamen(int idExam, int idEtud) {
        Optional<Examen> e = examenRepository.findById(idExam);
        Optional<Etudiant> et = etudiantRepository.findById(idEtud);
        if (e.isPresent() && et.isPresent()) {
            Examen examen = e.get();
            Etudiant etudiant = et.get();
            examen.getEtudiants().add(etudiant);
            return examenRepository.save(examen);
        }
        return null;
    }


    @Override
    public Double getStudentExamResult(int etudiantId, int examenId) {
        Optional<Examen> examenOptional = examenRepository.findByIdEx(examenId);
        if (examenOptional.isPresent()) {
            Examen examen = examenOptional.get();
            for (Etudiant etudiant : examen.getEtudiants()) {
                if (etudiant.getIdEtud() == etudiantId) {
                    return (double) examen.getNote();
                }
            }
        }
        return null;
    }

    @Override
    public List<Examen> getAllExams() {
        return examenRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteExamen(int id) {
        questionRepository.deleteByExamenIdEx(id);
        examenRepository.deleteById(id);
    }
}

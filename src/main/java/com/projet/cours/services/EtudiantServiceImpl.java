package com.projet.cours.services;

import com.projet.cours.entities.Cours;
import com.projet.cours.entities.Etudiant;
import com.projet.cours.repositories.CoursRepository;
import com.projet.cours.repositories.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EtudiantServiceImpl implements IEtudiantService{
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private CoursRepository coursRepository;
    public boolean isEtudiantEnrolledInCours(Etudiant etudiant, Cours cours) {
        return etudiant.getCours() != null && etudiant.getCours().equals(cours);
    }
    @Override
    public Etudiant saveEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    @Override
    public Etudiant updateEtudiant(Etudiant etudiant, int id) {
         etudiant.setIdEtud(id);
        return etudiantRepository.save(etudiant);

    }

    @Override
    public void deleteEtudiant(int id) {
    etudiantRepository.deleteById(id);
    }

    @Override
    public Etudiant getEtudiantByid(int id) {
        Optional<Etudiant> etudiant=etudiantRepository.findById(id);
        if(etudiant.isPresent())
            return etudiant.get();
        return  null;    }

    @Override
    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public List<Etudiant> getStudentsByCours(int coursId) {
        Optional<Cours> coursOpt = coursRepository.findById(coursId);

        if (coursOpt.isPresent()) {
            Cours cours = coursOpt.get();
            return etudiantRepository.findByCours(cours);
        }
        return null;
    }

    @Override
    public List<Cours> getEtudiantCours(int etudiantId) {
        Optional<Etudiant> etudiantOptional = etudiantRepository.findById(etudiantId);
        if (etudiantOptional.isPresent()) {
            return etudiantOptional.get().getCours();
        } else {
            System.out.println("Etudiant not found with ID: " + etudiantId);
            return null;
        }
    }

    @Override
    public void signUpForCourse(int etudiantId, int coursId) {
        Optional<Etudiant> etudiantOpt = etudiantRepository.findById(etudiantId);
        Optional<Cours> coursOpt = coursRepository.findById(coursId);

        if (etudiantOpt.isPresent() && coursOpt.isPresent()) {
            Etudiant etudiant = etudiantOpt.get();
            Cours cours = coursOpt.get();

            if (!etudiant.getCours().contains(cours)) {
                etudiant.getCours().add(cours);
                etudiantRepository.save(etudiant);
                System.out.println("Student enrolled successfully.");
            } else {
                System.out.println("Student is already enrolled in this course.");
            }
        } else {
            System.out.println("Student or course not found.");
        }
    }
    @Override
    public List<String> getCoursMaterials(int coursId) {
        Optional<Cours> coursOpt = coursRepository.findById(coursId);
        return coursOpt.map(Cours::getMaterials).orElse(null);
    }
}

package com.projet.cours.services;

import com.projet.cours.entities.Cours;
import com.projet.cours.entities.Enseignant;
import com.projet.cours.repositories.CoursRepository;
import com.projet.cours.repositories.EnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class EnseignantServiceImpl implements IEnseignantService {
    @Autowired
    private EnseignantRepository enseignantRepository;

    @Autowired
    private CoursRepository coursRepository;

    @Override
    public Enseignant saveEnseignant(Enseignant enseignant) {
        return enseignantRepository.save(enseignant);
    }

    @Override
    public Enseignant updateEnseignant(int id, Enseignant enseignant) {
        enseignant.setIdEns(id);
        return enseignantRepository.save(enseignant);
    }

    @Override
    public Enseignant assignCoursToEnseignant(int enseignantId, int coursId) {
        Optional<Enseignant> ens = enseignantRepository.findById(enseignantId);
        Optional<Cours> cou = coursRepository.findById(coursId);

        if (ens.isPresent() && cou.isPresent()) {
            Enseignant enseignant = ens.get();
            Cours cours = cou.get();
            enseignant.getCours().add(cours);
            cours.setEnseignant(enseignant);
            coursRepository.save(cours);
            return enseignantRepository.save(enseignant);
        }
        return null;
    }

    @Override
    public List<Enseignant> getAllEnseignants() {
        return enseignantRepository.findAll();
    }

    @Override
    public List<Cours> getCoursesByEnseignant(int enseignantId) {
        Optional<Enseignant> enseignantOpt = enseignantRepository.findById(enseignantId);
        if (enseignantOpt.isPresent()) {
            Enseignant enseignant = enseignantOpt.get();
            return enseignant.getCours();
        }
        return new ArrayList<>();
    }

    @Override
    public void deleteEnseignant(int id) {
        enseignantRepository.deleteById(id);
    }
}

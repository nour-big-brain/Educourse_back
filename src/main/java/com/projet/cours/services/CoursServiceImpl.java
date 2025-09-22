package com.projet.cours.services;

import com.projet.cours.entities.Cours;
import com.projet.cours.entities.Examen;
import com.projet.cours.repositories.CoursRepository;
import com.projet.cours.repositories.ExamenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CoursServiceImpl implements ICoursService{
    @Autowired
    private CoursRepository coursRepository;
    @Autowired
    private ExamenRepository examenRepository;
    @Override
    public Cours saveCours(Cours cours) {
            return coursRepository.save(cours);
    }

    @Override
    public Cours updateCours(Cours cours,int id) {
        cours.setIdCours(id);
        return coursRepository.save(cours);
    }
    public String saveImage(int courseId, MultipartFile file) throws IOException {
        Optional<Cours> coursOpt = coursRepository.findById(courseId);
        if (coursOpt.isEmpty()) {
            throw new RuntimeException("Course not found");
        }

        Cours cours = coursOpt.get();
        String imageBase64 = Base64.getEncoder().encodeToString(file.getBytes());
        cours.setImage(imageBase64);
        coursRepository.save(cours);
        System.out.println("Course after image saved: " + cours.getImage().substring(0, 30));

        return "Image uploaded successfully";
    }

    @Override
    public void deleteCours(int id) {
         coursRepository.deleteById(id);

    }

    @Override
    public List<Cours> getAllCours() {
        return coursRepository.findAll();
    }

    @Override
    public Cours getCoursById(int id) {
        Optional <Cours>c= coursRepository.findById(id);
        if(c.isPresent()){
            return c.get();
        }
        return null;
    }
    public void addCourseMaterial(int courseId, String materialUrl) {
        Optional<Cours> coursOpt = coursRepository.findById(courseId);
        if (coursOpt.isPresent()) {
            Cours cours = coursOpt.get();
            cours.getMaterials().add(materialUrl);
            coursRepository.save(cours);
        }
    }
    @Override
    public Cours assignExamToCourse(int coursId, int examenId) {
        Cours cours = coursRepository.findById(coursId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        Examen examen = examenRepository.findById(examenId)
                .orElseThrow(() -> new RuntimeException("Exam not found"));

        examen.setCours(cours);
        examenRepository.save(examen);

        return cours;
    }

    @Override
    public List<Examen> getCourseExams(int coursId) {
        return examenRepository.findByCoursIdCours(coursId);
    }

    @Override
    public List<String> getCourseMaterials(int coursId) {
        Optional<Cours> optionalCours = coursRepository.findById(coursId);
        if (optionalCours.isPresent()) {
            Cours cours = optionalCours.get();
            return cours.getMaterials() != null ? cours.getMaterials() : Collections.emptyList();
        } else {
            return Collections.emptyList();
        }
    }
    }



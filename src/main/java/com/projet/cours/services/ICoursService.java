package com.projet.cours.services;

import com.projet.cours.entities.Cours;
import com.projet.cours.entities.Examen;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ICoursService {
    public Cours saveCours(Cours cours);
    public Cours updateCours(Cours cours,int id);
    public void deleteCours(int id);
    public List<Cours> getAllCours();
    public Cours getCoursById(int id);
    public void addCourseMaterial(int courseId, String materialUrl);
    Cours assignExamToCourse(int coursId, int examenId);
    List<Examen> getCourseExams(int coursId);
    List<String> getCourseMaterials(int coursId);
    public String saveImage(int courseId, MultipartFile file) throws IOException;


}

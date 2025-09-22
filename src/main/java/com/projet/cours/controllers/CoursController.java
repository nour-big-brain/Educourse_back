package com.projet.cours.controllers;

import com.projet.cours.entities.Cours;
import com.projet.cours.entities.Examen;
import com.projet.cours.services.ICoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
public class CoursController {
    @Autowired
    private ICoursService iCoursService;
    @GetMapping("/cours")
    public ResponseEntity<List<Cours>> getCourses() {
        List<Cours> courses = iCoursService.getAllCours();
        return ResponseEntity.ok().body(courses);
    }


    @PostMapping("/cours")
    public ResponseEntity<Cours> saveCoursWithImage(
            @RequestParam("titre_cours") String titreCours,
            @RequestParam("duree_cours") int dureeCours,
            @RequestParam(value = "image", required = false) MultipartFile file) {
        try {
            // Create a new course
            Cours cours = new Cours();
            cours.setTitre_cours(titreCours);
            cours.setDuree_cours(dureeCours);

            // Save the course (without image)
            Cours savedCours = iCoursService.saveCours(cours);
            System.out.println("Received Cours: " + cours.getTitre_cours() + ", Duration: " + cours.getDuree_cours());


            // If an image is provided, save it and get the updated course
            if (file != null && !file.isEmpty()) {
                iCoursService.saveImage(savedCours.getIdCours(), file);
                // Re-fetch the updated course from DB
                savedCours = iCoursService.getCoursById(savedCours.getIdCours());
            }

            return ResponseEntity.ok(savedCours);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("cours/{id}")
    public void deleteCours(@PathVariable("id") int id){
        iCoursService.deleteCours(id);
    }
    @GetMapping("/cours/{id}")
    public Cours getCoursById(@PathVariable("id") int id){
        return iCoursService.getCoursById(id);
    }

    @PutMapping("/cours/{id}")
    public Cours updateCours(@RequestBody Cours cours,@PathVariable("id") int id){
        return iCoursService.updateCours(cours,id);
    }
    @PostMapping("/cours/{id}/materials")
    public ResponseEntity<String> addCourseMaterial(@PathVariable("id") int id, @RequestBody String materialUrl) {
        iCoursService.addCourseMaterial(id, materialUrl);
        return ResponseEntity.ok("Material added successfully");
    }
    @PostMapping("/cours/{coursId}/exams/{examenId}")
    public ResponseEntity<Cours> assignExamToCourse(
            @PathVariable int coursId,
            @PathVariable int examenId) {
        Cours cours = iCoursService.assignExamToCourse(coursId, examenId);
        return ResponseEntity.ok(cours);
    }

    @GetMapping("/cours/{coursId}/exams")
    public ResponseEntity<List<Examen>> getCourseExams(
            @PathVariable int coursId) {
        List<Examen> exams = iCoursService.getCourseExams(coursId);
        return ResponseEntity.ok(exams);
    }
    @GetMapping("/cours/{coursId}/materials")
    public ResponseEntity<List<String>> getCourseMaterials(@PathVariable int coursId) {
        List<String> materials = iCoursService.getCourseMaterials(coursId);
        return ResponseEntity.ok(materials);
    }
}

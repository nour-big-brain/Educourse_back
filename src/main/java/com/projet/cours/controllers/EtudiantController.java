package com.projet.cours.controllers;

import com.projet.cours.entities.Cours;
import com.projet.cours.entities.Etudiant;
import com.projet.cours.services.IEtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EtudiantController {
    @Autowired
    private IEtudiantService iEtudiantService;
    @GetMapping("/etudiants")
    public List<Etudiant> getAll(){
        return iEtudiantService.getAllEtudiants();
    }
    @PostMapping("/etudiants")
    public Etudiant saveEtudiant(@RequestBody Etudiant et){
        return iEtudiantService.saveEtudiant(et);
    }
    @DeleteMapping("etudiant/{id}")
    public void deleteEtudiant(@PathVariable("id") int id){
        iEtudiantService.deleteEtudiant(id);
    }
    @GetMapping("/etudiants/{id}")
    public Etudiant getOne(@PathVariable("id")int id){
        return iEtudiantService.getEtudiantByid(id);
    }
    @PutMapping("/etudiants/{id}")
    public Etudiant updateEtudiant(@RequestBody Etudiant etudiant,@PathVariable("id") int id){
        return iEtudiantService.updateEtudiant(etudiant,id);
    }
    @GetMapping("etudiantBycours/{id}")
    public List<Etudiant> get(@PathVariable("id")int coursId){
        return iEtudiantService.getStudentsByCours(coursId);
    }
    @GetMapping("/etudiants/{id}/cours")
    public List<Cours> getEtudiantCours(@PathVariable("id") int id) {
        return iEtudiantService.getEtudiantCours(id);
    }
    @PostMapping("etudiants/{id}/signup/{coursId}")
    public ResponseEntity<Map<String, String>> signUpForCourse(@PathVariable("id") int id, @PathVariable("coursId") int coursId) {
        iEtudiantService.signUpForCourse(id, coursId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Signed up for course successfully");
        return ResponseEntity.ok(response);
    }


}

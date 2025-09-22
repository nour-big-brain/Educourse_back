package com.projet.cours.controllers;


import com.projet.cours.entities.Cours;
import com.projet.cours.entities.Enseignant;
import com.projet.cours.services.IEnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EnseignantController {
    @Autowired
    private IEnseignantService iEnseignantService;
    @GetMapping("/enseignants")
    public List<Enseignant> getAll(){
        return iEnseignantService.getAllEnseignants();
    }
    @PostMapping("/enseignants")
    public Enseignant saveEnseignant(@RequestBody Enseignant enseignant){
        return iEnseignantService.saveEnseignant(enseignant);
    }
    @GetMapping("/coursbyenseignant/{id}")
    public List<Cours> getCoursByEnseignant(@PathVariable("id") int id){
        return iEnseignantService.getCoursesByEnseignant(id);
    }

    @PutMapping("/enseignants/{id}")
    public Enseignant updateEnseignant(@RequestBody Enseignant enseignant,@PathVariable("id") int id){
        return iEnseignantService.updateEnseignant(id,enseignant);
    }
    @DeleteMapping("/enseignants/{id}")
    public void deleteEnseignant(@PathVariable("id") int id) {
        iEnseignantService.deleteEnseignant(id);
    }
    @PutMapping("/enseignants/{enseignantId}/cours/{coursId}")
    public Enseignant assignCoursToEnseignant(
            @PathVariable("enseignantId") int enseignantId,
            @PathVariable("coursId") int coursId) {
        return iEnseignantService.assignCoursToEnseignant(enseignantId, coursId);
    }


}

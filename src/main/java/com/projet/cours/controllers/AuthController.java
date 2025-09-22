package com.projet.cours.controllers;

import com.projet.cours.entities.Etudiant;
import com.projet.cours.entities.Enseignant;
import com.projet.cours.repositories.EtudiantRepository;
import com.projet.cours.repositories.EnseignantRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@SessionAttributes("userRole")
public class AuthController {

    private final EtudiantRepository etudiantRepository;
    private final EnseignantRepository enseignantRepository;

    public AuthController(EtudiantRepository etudiantRepository, EnseignantRepository enseignantRepository) {
        this.etudiantRepository = etudiantRepository;
        this.enseignantRepository = enseignantRepository;
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session) {
        Optional<Etudiant> etudiantOpt = etudiantRepository.findByEmailEtud(email);
        if (etudiantOpt.isPresent() && etudiantOpt.get().getPassword().equals(password)) {
            Etudiant etudiant = etudiantOpt.get();
            session.setAttribute("userRole", etudiant.getRole());
            session.setAttribute("etudiantId", etudiant.getIdEtud());
            return "Logged in as STUDENT | ID:" + etudiant.getIdEtud();
        }

        Optional<Enseignant> enseignantOpt = enseignantRepository.findByEmailEns(email);
        if (enseignantOpt.isPresent() && enseignantOpt.get().getPassword().equals(password)) {
            Enseignant enseignant = enseignantOpt.get();
            session.setAttribute("userRole", "TEACHER");
            session.setAttribute("enseignantId", enseignant.getIdEns()); // Store teacher ID in session
            return "Logged in as TEACHER | ID:" + enseignant.getIdEns(); // Include teacher ID in response
        }

        return "Invalid credentials";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logged out";
    }

    @PostMapping("/register")
    public String register(@RequestBody Etudiant etudiant) {
        Optional<Etudiant> existingEtudiant = etudiantRepository.findByEmailEtud(etudiant.getEmailEtud());
        if (existingEtudiant.isPresent()) {
            return "Student already exists with this email";
        }

        etudiantRepository.save(etudiant);
        return "Student registered successfully";
    }
}

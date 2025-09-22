package com.projet.cours.repositories;

import com.projet.cours.entities.Cours;
import com.projet.cours.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EtudiantRepository extends JpaRepository<Etudiant,Integer> {
    List<Etudiant> findByCours(Cours cours);
    Optional<Etudiant> findByEmailEtud(String emailEtud);

}

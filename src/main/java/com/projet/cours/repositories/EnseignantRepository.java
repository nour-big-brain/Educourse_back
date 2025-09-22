package com.projet.cours.repositories;

import com.projet.cours.entities.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnseignantRepository extends JpaRepository<Enseignant,Integer> {
    Optional<Enseignant> findByEmailEns(String emailEns);
}

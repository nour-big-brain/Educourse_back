package com.projet.cours.repositories;

import com.projet.cours.entities.Examen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ExamenRepository extends JpaRepository<Examen, Integer> {
    Optional<Examen> findByIdEx(int examenId);
    List<Examen> findByCoursIdCours(int coursId);
}

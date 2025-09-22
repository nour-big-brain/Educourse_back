package com.projet.cours.repositories;

import com.projet.cours.entities.Cours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursRepository extends JpaRepository<Cours,Integer> {
}

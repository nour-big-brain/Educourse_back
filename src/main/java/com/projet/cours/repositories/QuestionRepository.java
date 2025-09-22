package com.projet.cours.repositories;

import com.projet.cours.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByExamenIdEx(int examenId);
    void deleteByExamenIdEx(int examenId);

}

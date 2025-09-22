package com.projet.cours.services;

import com.projet.cours.entities.Examen;
import com.projet.cours.entities.Question;
import com.projet.cours.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QuestionServiceImpl implements IQuestionService {
    @Autowired
    private QuestionRepository questionRepository;


    public List<Question> getQuestionsByExamId(int examId) {
        return questionRepository.findByExamenIdEx(examId);
    }

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    public void deleteQuestion(int questionId) {
        questionRepository.deleteById(questionId);
    }
    public Question assignToExam(Question question, int examId) {
        Examen examen = new Examen();
        examen.setIdEx(examId);
        question.setExamen(examen);
        return questionRepository.save(question);
    }

}

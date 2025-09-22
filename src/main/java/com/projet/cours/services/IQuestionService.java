package com.projet.cours.services;

import com.projet.cours.entities.Question;

import java.util.List;

public interface IQuestionService {
    public List<Question> getQuestionsByExamId(int examId);
    public Question saveQuestion(Question question);
    public void deleteQuestion(int questionId);
    public Question assignToExam(Question question, int examId);
}

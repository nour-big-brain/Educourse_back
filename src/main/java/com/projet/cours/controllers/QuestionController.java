package com.projet.cours.controllers;

import com.projet.cours.entities.Question;
import com.projet.cours.services.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class QuestionController {
    @Autowired
    private IQuestionService questionService;
    @GetMapping("/questions/exam/{examId}")
    public List<Question> getQuestionsByExam(@PathVariable int examId) {
        return questionService.getQuestionsByExamId(examId);
    }
    @PostMapping("/questions")
    public Question addQuestion(@RequestBody Question question) {
        return questionService.saveQuestion(question);
    }
    @DeleteMapping("questions/{id}")
    public void deleteQuestion(@PathVariable int id) {
        questionService.deleteQuestion(id);
    }
    @PostMapping("/questions/assign-to-exam/{examId}")
    public Question assignQuestionToExam(@RequestBody Question question, @PathVariable int examId) {
        return questionService.assignToExam(question, examId);
    }

}


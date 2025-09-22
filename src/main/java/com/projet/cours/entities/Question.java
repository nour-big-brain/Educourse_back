package com.projet.cours.entities;

import jakarta.persistence.*;

@Entity
public class Question {

    @Id
    @GeneratedValue
    private int id;

    private String content;
    private String optionA;
    private String optionB;
    private String optionC;
    private String correctAnswer;
    private String points;

    @ManyToOne
    @JoinColumn(name = "examen_id")
    private Examen examen;


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getOptionA() { return optionA; }
    public void setOptionA(String optionA) { this.optionA = optionA; }

    public String getOptionB() { return optionB; }
    public void setOptionB(String optionB) { this.optionB = optionB; }

    public String getOptionC() { return optionC; }
    public void setOptionC(String optionC) { this.optionC = optionC; }

    public String getCorrectAnswer() { return correctAnswer; }
    public void setCorrectAnswer(String correctAnswer) { this.correctAnswer = correctAnswer; }

    public Examen getExamen() { return examen; }
    public void setExamen(Examen examen) { this.examen = examen; }

    public String getPoints() {

        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}

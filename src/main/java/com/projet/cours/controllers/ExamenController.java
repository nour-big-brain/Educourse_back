package com.projet.cours.controllers;

import com.projet.cours.entities.Examen;
import com.projet.cours.entities.Question;
import com.projet.cours.repositories.ExamenRepository;
import com.projet.cours.repositories.QuestionRepository;
import com.projet.cours.services.IExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class ExamenController {
    @Autowired
    private IExamenService iExamenService;
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ExamenRepository examenRepository;

    @PostMapping("/examens")
    public Examen saveExamen(@RequestBody Examen examen) {
        return iExamenService.saveExamen(examen);
    }
    @DeleteMapping("/examens/{id}")
    public void deleteExamen(@PathVariable("id") int id){
        iExamenService.deleteExamen(id);
    }
    @PutMapping("/examens/{id}/schedule")
    public Examen scheduleExamen(@PathVariable("id") int id, @RequestParam("date") Date date_ex) {
        return iExamenService.scheduleExamen(id, date_ex);
    }

    @PutMapping("/examens/{idExam}/assign/{idEtud}")
    public Examen assignEtudiantToExamen(@PathVariable("idExam") int idExam, @PathVariable("idEtud") int idEtud) {
        return iExamenService.assignEtudiantToExamen(idExam, idEtud);
    }

    @GetMapping("/examens/{idExam}/result/{idEtud}")
    public Double getStudentExamResult(@PathVariable("idEtud") int etudiantId, @PathVariable("idExam") int examenId) {
        return iExamenService.getStudentExamResult(etudiantId, examenId);
    }

    @GetMapping("/examens")
    public List<Examen> getAllExams() {
        return iExamenService.getAllExams();
    }
    @PostMapping("/examens/{examId}/submit")
    public ResponseEntity<Map<String, Object>> submitExam(
            @PathVariable int examId,
            @RequestBody Map<Integer, String> studentAnswers
    ) {
        List<Question> questions = questionRepository.findByExamenIdEx(examId);
        int total = questions.size();
        int correct = 0;

        for (Question q : questions) {
            String correctOption = q.getCorrectAnswer();
            String studentAnswer = studentAnswers.get(q.getId());
            if (correctOption.equalsIgnoreCase(studentAnswer)) {
                correct++;
            }
        }

        int score = (correct * 100) / total;
        boolean passed = score >= 50;
        Examen examen = examenRepository.findById(examId).orElseThrow(() -> new RuntimeException("Examen not found"));
        examen.setNote(score);
        examenRepository.save(examen);
        Map<String, Object> result = new HashMap<>();
        result.put("score", score);
        result.put("passed", passed);

        return ResponseEntity.ok(result);
    }

}

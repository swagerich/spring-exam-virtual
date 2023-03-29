package com.erich.exam.controllers;

import static com.erich.exam.util.Path.PATH;

import com.erich.exam.dto.QuestionDto;
import com.erich.exam.entity.Question;
import com.erich.exam.entity.Quiz;
import com.erich.exam.services.impl.QuestionServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PATH + "question")
@CrossOrigin("*")
public class QuestionController {

    private final QuestionServiceImpl questionService;

    public QuestionController(QuestionServiceImpl questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody QuestionDto question) {
        return new ResponseEntity<>(questionService.saveQuestion(question), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody QuestionDto question) {
        return new ResponseEntity<>(questionService.updateQuestion(question), HttpStatus.CREATED);
    }

    @GetMapping("{questionId}")
    public ResponseEntity<?> getQuestionId(@PathVariable Long questionId) {
        return new ResponseEntity<>(questionService.findQuestionById(questionId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAllQuestions() {
        return new ResponseEntity<>(questionService.findAllQuestion(), HttpStatus.OK);
    }

    @GetMapping("/of-quiz/{qid}")
    public ResponseEntity<?> findQuestionByOfQiz(@PathVariable Long qid) {
        return new ResponseEntity<>(questionService.getQuestionsOfQuiz(qid), HttpStatus.OK);
    }

    @GetMapping("/quiz/all/{qid}")
    public ResponseEntity<?> findQuestionByOfQizAdmin(@PathVariable Long qid) {
        Quiz q = new Quiz();
        q.setId(qid);
        return new ResponseEntity<>(questionService.findByQuiz(q), HttpStatus.OK);
    }


    @DeleteMapping("{questionId}")
    public ResponseEntity<?> deleteByQuestionId(@PathVariable Long questionId) {
        questionService.deleteQuestionById(questionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/eval-quiz")
    public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questionList){
        return new ResponseEntity<>(questionService.EvaluationQuizze(questionList),HttpStatus.OK);
    }

}

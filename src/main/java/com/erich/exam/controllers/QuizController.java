package com.erich.exam.controllers;

import static com.erich.exam.util.Path.PATH;

import com.erich.exam.dto.QuizDto;
import com.erich.exam.entity.Quiz;
import com.erich.exam.services.impl.QuizServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PATH + "quizze")
@CrossOrigin("*")
public class QuizController {

    private final QuizServiceImpl quizService;

    public QuizController(QuizServiceImpl quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody QuizDto quiz){
        return new ResponseEntity<>(quizService.saveQuiz(quiz), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody QuizDto quiz){
        return new ResponseEntity<>(quizService.updateQuiz(quiz), HttpStatus.CREATED);
    }

    @GetMapping("{quizId}")
    public ResponseEntity<?> getQuizzesId(@PathVariable Long quizId){
        return new ResponseEntity<>(quizService.findQuizzesById(quizId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAllQuizzes(){
        return new ResponseEntity<>(quizService.getAllQuizzes(), HttpStatus.OK);
    }

    @DeleteMapping("{quizId}")
    public ResponseEntity<?> deleteByQuizzeId(@PathVariable Long quizId){
        quizService.deleteQuizzesById(quizId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/category/{cId}")
    public ResponseEntity<?> findCategoryById(@PathVariable Long cId){
//        Category c = new Category();
//        c.setId(cId);
        return new ResponseEntity<>(quizService.findQuizzeCategoryId(cId),HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<?> getActiveQuizzes(){
        return new ResponseEntity<>(quizService.getActiveQuizzes(),HttpStatus.OK);
    }

    @GetMapping("/category/active/{cId}")
    public ResponseEntity<?> getActiveQuizzesOfCategory(@PathVariable Long cId){
        return new ResponseEntity<>(quizService.getActiveQuizzesOfCategoryId(cId),HttpStatus.OK);
    }

}

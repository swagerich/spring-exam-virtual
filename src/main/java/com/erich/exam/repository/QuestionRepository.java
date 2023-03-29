package com.erich.exam.repository;

import com.erich.exam.entity.Question;
import com.erich.exam.entity.Quiz;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface QuestionRepository extends CrudRepository<Question, Long> {

    Set<Question> findByQuiz(Quiz quiz);
}
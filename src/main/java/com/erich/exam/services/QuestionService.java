package com.erich.exam.services;

import com.erich.exam.dto.QuestionDto;
import com.erich.exam.entity.Question;
import com.erich.exam.entity.Quiz;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface QuestionService {

    QuestionDto saveQuestion(QuestionDto question);

    QuestionDto updateQuestion(QuestionDto question);

    Set<QuestionDto> findAllQuestion();

    QuestionDto findQuestionById(Long idQuestion);

    Set<QuestionDto> getQuestionsOfQuiz(Long quiz);

    Set<QuestionDto> findByQuiz(Quiz quiz);

    void deleteQuestionById(Long idQuestion);

    Map<String,Integer> EvaluationQuizze(List<Question> questionList);

}

package com.erich.exam.services;

import com.erich.exam.dto.QuizDto;
import com.erich.exam.entity.Category;
import com.erich.exam.entity.Quiz;

import java.util.List;
import java.util.Set;

public interface QuizService {

    QuizDto saveQuiz(QuizDto quiz);

    QuizDto updateQuiz(QuizDto quiz);

    Set<QuizDto> getAllQuizzes();

    QuizDto findQuizzesById(Long idQuizzes);

    void deleteQuizzesById(Long idQuizzes);

    List<QuizDto> findQuizzeCategory(Category category);
    List<QuizDto> findQuizzeCategoryId(Long categoryID);

    List<QuizDto> getActiveQuizzes();

    List<QuizDto> getActiveQuizzesOfCategory(Category cat);

    List<QuizDto> getActiveQuizzesOfCategoryId(Long catId);

}

package com.erich.exam.repository;

import com.erich.exam.entity.Category;
import com.erich.exam.entity.Quiz;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuizRepository extends CrudRepository<Quiz, Long> {

    List<Quiz> findByCategory(Category category);

    List<Quiz> findByCategoryId(Long categoryId);

    List<Quiz> findByActive(Boolean isActive);

    List<Quiz> findByCategoryAndActive(Category cat, Boolean isActive);

    List<Quiz> findByCategoryIdAndActive(Long catId, Boolean isActive);

}
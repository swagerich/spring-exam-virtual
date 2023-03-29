package com.erich.exam.services.impl;

import com.erich.exam.dto.QuizDto;
import com.erich.exam.entity.Category;
import com.erich.exam.entity.Quiz;
import com.erich.exam.exception.NotFoundException;
import com.erich.exam.repository.QuizRepository;
import com.erich.exam.services.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepo;

    @Override
    @Transactional
    public QuizDto saveQuiz(QuizDto quiz) {
        Quiz q = QuizDto.toEntity(quiz);
        return QuizDto.fromEntity(quizRepo.save(q));
    }

    @Override
    @Transactional
    public QuizDto updateQuiz(QuizDto quiz) {
        Quiz q = QuizDto.toEntity(quiz);
        return QuizDto.fromEntity(quizRepo.save(q));
    }

    @Override
    @Transactional(readOnly = true)
    public Set<QuizDto> getAllQuizzes() {
        return Streamable.of(quizRepo.findAll())
                .stream().map(QuizDto::fromEntity).collect(Collectors.toSet());
    }

    @Override
    @Transactional(readOnly = true)
    public QuizDto findQuizzesById(Long idQuizzes) {

        return QuizDto.fromEntity(quizRepo.findById(idQuizzes).orElseThrow(() -> new NotFoundException("Quiz id not found")));
    }

    @Override
    @Transactional
    public void deleteQuizzesById(Long idQuizzes) {
        if (idQuizzes == null) {
            return;
        }
        quizRepo.deleteById(idQuizzes);
    }

    @Override
    @Transactional(readOnly = true)
    public  List<QuizDto> findQuizzeCategory(Category category) {
        return Streamable.of(quizRepo.findByCategory(category))
                .stream()
                .map(QuizDto::fromEntity)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuizDto> findQuizzeCategoryId(Long categoryID) {
        return Streamable.of(quizRepo.findByCategoryId(categoryID))
                .stream()
                .map(QuizDto::fromEntity)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuizDto> getActiveQuizzes() {
        return Streamable.of(quizRepo.findByActive(true))
                .stream().map(QuizDto::fromEntity).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuizDto> getActiveQuizzesOfCategory(Category cat) {
        return Streamable.of(quizRepo.findByCategoryAndActive(cat,true))
                .stream().map(QuizDto::fromEntity).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuizDto> getActiveQuizzesOfCategoryId(Long catId) {
        return Streamable.of(quizRepo.findByCategoryIdAndActive(catId,true))
                .stream().map(QuizDto::fromEntity).toList();
    }
}

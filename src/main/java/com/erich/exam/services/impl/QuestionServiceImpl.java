package com.erich.exam.services.impl;

import com.erich.exam.dto.QuestionDto;
import com.erich.exam.entity.Question;
import com.erich.exam.entity.Quiz;
import com.erich.exam.exception.NotFoundException;
import com.erich.exam.repository.QuestionRepository;
import com.erich.exam.repository.QuizRepository;
import com.erich.exam.services.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepo;

    private final QuizRepository quizRepo;

    @Override
    @Transactional
    public QuestionDto saveQuestion(QuestionDto question) {
        Question q = QuestionDto.toEntity(question);

        return QuestionDto.fromEntity(questionRepo.save(q));
    }

    @Override
    @Transactional
    public QuestionDto updateQuestion(QuestionDto question) {
        Question q = QuestionDto.toEntity(question);
        return QuestionDto.fromEntity(questionRepo.save(q));
    }

    @Override
    @Transactional(readOnly = true)
    public Set<QuestionDto> findAllQuestion() {
        return Streamable.of(questionRepo.findAll())
                .stream()
                .map(QuestionDto::fromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional(readOnly = true)
    public QuestionDto findQuestionById(Long idQuestion) {
        return QuestionDto.fromEntity(questionRepo.findById(idQuestion).orElseThrow(() -> new NotFoundException("Questions id not found")));
    }

    @Override
    @Transactional(readOnly = true)
    public Set<QuestionDto> getQuestionsOfQuiz(Long quiz) {
        Quiz q = quizRepo.findById(quiz).orElseThrow(() -> new NotFoundException("id quiz It was not found"));
        Set<Question> questionSet = q.getQuestions();

        List<Question> questList = new ArrayList<>(questionSet);

        //VALIDAMOS LAS PREGUNTAS NO SEAN MAYORES QUE  EL NUMERO DE PREGUNTAS
        if (questList.size() > Integer.parseInt(q.getNumberOfQuestions())) {
            questList = questList.subList(0, Integer.parseInt(q.getNumberOfQuestions() + 1));
//            throw new ResourceException("las preguntas no puede ser mayor que los numero preguntas del cuestonario");
        }
        // SETEAMOS A UN STRING VACIO PARA OCULTAR LA RESPUESTA.
        questList.forEach(x ->{
            x.setAnswer("");
        });

        //MANDAMOS EL ARREGLO RANDOM PARA LAS PREGUNTAS
        Collections.shuffle(questList);
        return Streamable.of(new HashSet<>(questList)).stream()
                .map(QuestionDto::fromEntity).collect(Collectors.toSet());
    }

    @Override
    @Transactional(readOnly = true)
    public Set<QuestionDto> findByQuiz(Quiz quiz) {
        return Streamable.of(questionRepo.findByQuiz(quiz))
                .stream().map(QuestionDto::fromEntity).collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public void deleteQuestionById(Long idQuestion) {
        if (idQuestion == null) {
            return;
        }
        questionRepo.deleteById(idQuestion);
    }

    @Override
    public Map<String,Integer> EvaluationQuizze(List<Question> questions) {

        //puntuacion
        int marksGot = 0;
        //preguntasCorrectas
        int correctAnswer = 0;
        //intentos
        int attempted = 0;

        for (Question q : questions) {
            Question qId = questionRepo.findById(q.getId()).orElseThrow(() -> new NotFoundException("ID not found questions"));
            if (qId.getAnswer().equals(q.getGivenAnswer())) {
                correctAnswer++;
                int points = Integer.parseInt(questions.get(0).getQuiz().getMaxMarks()) / questions.size();
                marksGot += points;
            }
            if (q.getGivenAnswer() != null || !q.getGivenAnswer().trim().equals("")) {
                attempted++;
            }
        }
        return Map.of("marksGot", marksGot,
                "correctAnswer", correctAnswer,
                "attempted", attempted);
    }
}


package com.erich.exam.dto;

import com.erich.exam.entity.Quiz;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuizDto {

    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    @Column(length = 500)
    private String description;

    @NotBlank
    private String maxMarks;

    @NotBlank
    private String numberOfQuestions;

    private boolean active;

    private CategoryDto category;

    @JsonIgnore
    private Set<QuestionDto> questions;

    public static QuizDto fromEntity(Quiz quiz){
       if(quiz == null){
           return null;
       }
       return QuizDto.builder()
               .id(quiz.getId())
               .title(quiz.getTitle())
               .description(quiz.getDescription())
               .maxMarks(quiz.getMaxMarks())
               .numberOfQuestions(quiz.getNumberOfQuestions())
               .active(quiz.isActive())
               .category(CategoryDto.fromEntity(quiz.getCategory()))
//               .questions(quiz.getQuestions() != null ?
//                       quiz.getQuestions()
//                               .stream()
//                               .map(QuestionDto::fromEntity)
//                               .collect(Collectors.toSet()) : null)
               .build();
    }

    public static Quiz toEntity(QuizDto quizDto){
        if(quizDto == null){
            return null;
        }
        return Quiz.builder()
                .id(quizDto.getId())
                .title(quizDto.getTitle())
                .description(quizDto.getDescription())
                .maxMarks(quizDto.getMaxMarks())
                .numberOfQuestions(quizDto.getNumberOfQuestions())
                .active(quizDto.isActive())
                .category(CategoryDto.toEntity(quizDto.getCategory()))
                .build();
    }
}

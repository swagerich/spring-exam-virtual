package com.erich.exam.dto;

import com.erich.exam.entity.Question;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionDto {

    private Long id;

    @NotBlank
    private String content;

    private String image;

    @NotBlank
    private String optionOne;

    @NotBlank
    private String optionTwo;

    @NotBlank
    private String optionThree;

    @NotBlank
    private String optionFour;

    @NotBlank
    private String answer;

    @Transient
    private String givenAnswer;

    private QuizDto quiz;

    public static QuestionDto fromEntity(Question question) {
        if (question == null) {
            return null;
        }
        return QuestionDto.builder()
                .id(question.getId())
                .content(question.getContent())
                .image(question.getImage())
                .optionOne(question.getOptionOne())
                .optionTwo(question.getOptionTwo())
                .optionThree(question.getOptionThree())
                .optionFour(question.getOptionFour())
                .answer(question.getAnswer())
                .quiz(QuizDto.fromEntity(question.getQuiz()))
                .build();
    }

    public static Question toEntity(QuestionDto questionDto) {
        if (questionDto == null) {
            return null;
        }
        return Question.builder()
                .id(questionDto.getId())
                .content(questionDto.getContent())
                .image(questionDto.getImage())
                .optionOne(questionDto.getOptionOne())
                .optionTwo(questionDto.getOptionTwo())
                .optionThree(questionDto.getOptionThree())
                .optionFour(questionDto.getOptionFour())
                .answer(questionDto.getAnswer())
                .quiz(QuizDto.toEntity(questionDto.getQuiz()))
                .build();
    }
}

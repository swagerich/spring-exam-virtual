package com.erich.exam.dto;

import com.erich.exam.entity.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {

    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    @Column(length = 500)
    private String description;

    @JsonIgnore
    private Set<QuizDto> quizzes;

    public static CategoryDto fromEntity(Category category){
        if(category == null){
            return null;
        }
        return CategoryDto.builder()
                .id(category.getId())
                .title(category.getTitle())
                .description(category.getDescription())
                .build();
    }

    public static Category toEntity(CategoryDto categoryDto){
        if(categoryDto == null){
            return null;
        }
        return Category.builder()
                .id(categoryDto.getId())
                .title(categoryDto.getTitle())
                .description(categoryDto.getDescription())
                .build();
    }
}

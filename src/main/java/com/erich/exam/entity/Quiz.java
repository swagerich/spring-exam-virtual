package com.erich.exam.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "quizes")
@Builder
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Column(name = "max_marks")
    private String maxMarks;

    @Column(name = "number_of_questions")
    private String numberOfQuestions;

    private boolean active = false;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_categories"))
    private Category category;

    //@JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "quiz")
    private Set<Question> questions = new HashSet<>();

}

package com.erich.exam.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

   // @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category", orphanRemoval = true) //EAGER
    private Set<Quiz> quizzes = new LinkedHashSet<>();

}

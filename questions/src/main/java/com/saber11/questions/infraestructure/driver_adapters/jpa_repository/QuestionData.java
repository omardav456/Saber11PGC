package com.saber11.questions.infraestructure.driver_adapters.jpa_repository;

import com.saber11.questions.domain.model.Area;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class QuestionData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Area area;
    @Column(nullable = false)
    private String  question;
    @Column(nullable = false)
    private String  answer;
    @Column(nullable = false)
    private String optionA;
    @Column(nullable = false)
    private String optionB;
    @Column(nullable = false)
    private String optionC;
    @Column(nullable = false)
    private String optionD;
    @Column(nullable = false)
    private String justification;

}

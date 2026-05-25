package com.saber11.questions.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Question {
    private Long id;
    private Area area;
    private String  question;
    private AnswerOption answerOption;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String justification;


}

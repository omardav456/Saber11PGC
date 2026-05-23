package com.saber11.questions.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Question {
    Long id;
    Area area;
    private String  question;
    private String  answer;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String justification;


}

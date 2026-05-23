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
    String  question;
    String  answer;
    String optionA;
    String optionB;
    String optionC;
    String optionD;
    String justification;


}

package com.saber11.questions.domain.usecase;

import com.saber11.questions.domain.model.Area;
import com.saber11.questions.domain.model.Question;
import com.saber11.questions.domain.model.gateway.QuestionGateway;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class QuestionUseCase {

    private final QuestionGateway questionGateway;


    public Question createQuestion(Question question){
        if(question == null){
            throw new IllegalArgumentException("Question cannot be null");
        }
        if (question.getArea() == null) {
            throw new IllegalArgumentException("Area id cannot be null");
        }
        if (question.getQuestion() == null || question.getQuestion().trim().isEmpty()) {
            throw new IllegalArgumentException("Question question cannot be null");
        }
        if (question.getAnswer()==null || question.getAnswer().trim().isEmpty()) {
            throw new IllegalArgumentException("Answer cannot be null");
        }
        if (question.getOptionA()==null  || question.getOptionA().trim().isEmpty()) {
            throw new IllegalArgumentException("Option A cannot be null");
        }
        if (question.getOptionB()==null || question.getOptionB().trim().isEmpty()) {
            throw new IllegalArgumentException("Option B cannot be null");
        }
        if (question.getOptionC()==null  || question.getOptionC().trim().isEmpty()) {
            throw new IllegalArgumentException("Option C cannot be null");
        }
        if (question.getOptionD()==null || question.getOptionD().trim().isEmpty()) {
            throw new IllegalArgumentException("Option D cannot be null");
        }
        if (question.getJustification()==null || question.getJustification().trim().isEmpty()) {
            throw new IllegalArgumentException("Justification cannot be null");
        }
        return questionGateway.createQuestion(question);
    }

    public Question findQuestionById(Long id){
        if(id == null){
            throw new IllegalArgumentException("Question id cannot be null");
        }
        return questionGateway.findQuestionById(id);
    }

    public List<Question> findQuestionsByArea(Area area){
        if(area == null){
            throw new IllegalArgumentException("Area cannot be null");
        }
        return questionGateway.findQuestionsByArea(area);
    }

    public List<Question> findAllQuestions(){
        return questionGateway.findAllQuestions();
    }

    public Question updateQuestion(Question question){
        if(question == null){
            throw new IllegalArgumentException("Question cannot be null");
        }
        if (question.getId() == null) {
            throw new IllegalArgumentException("Question id cannot be null");
        }
        if (question.getArea() == null) {
            throw new IllegalArgumentException("Area id cannot be null");
        }
        if (question.getQuestion() == null || question.getQuestion().trim().isEmpty()) {
            throw new IllegalArgumentException("Question question cannot be null");
        }
        if (question.getAnswer()==null || question.getAnswer().trim().isEmpty()) {
            throw new IllegalArgumentException("Answer cannot be null");
        }
        if (question.getOptionA()==null  || question.getOptionA().trim().isEmpty()) {
            throw new IllegalArgumentException("Option A cannot be null");
        }
        if (question.getOptionB()==null || question.getOptionB().trim().isEmpty()) {
            throw new IllegalArgumentException("Option B cannot be null");
        }
        if (question.getOptionC()==null  || question.getOptionC().trim().isEmpty()) {
            throw new IllegalArgumentException("Option C cannot be null");
        }
        if (question.getOptionD()==null || question.getOptionD().trim().isEmpty()) {
            throw new IllegalArgumentException("Option D cannot be null");
        }
        if (question.getJustification()==null || question.getJustification().trim().isEmpty()) {
            throw new IllegalArgumentException("Justification cannot be null");
        }
        return questionGateway.updateQuestion(question);
    }
    public void deleteQuestionById(Long id){
        if(id == null){
            throw new IllegalArgumentException("Question id cannot be null");
        }
        questionGateway.deleteQuestionById(id);
    }
}

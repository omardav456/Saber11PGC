package com.saber11.questions.infraestructure.driver_adapters.jpa_repository;

import com.saber11.questions.domain.model.Area;
import com.saber11.questions.domain.model.Question;
import com.saber11.questions.domain.model.gateway.QuestionGateway;
import com.saber11.questions.infraestructure.mapper.MapperQuestion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionDataGatewayImp  implements QuestionGateway {

    private final MapperQuestion mapperQuestion;
    private final QuestionDataJpaRepository questionDataJpaRepository;

    @Override
    public Question createQuestion(Question question) {
        QuestionData questionData = mapperQuestion.toQuestionData(question);
        return mapperQuestion.toQuestion(questionDataJpaRepository.save(questionData));
    }

    @Override
    public Question findQuestionById(Long id) {
        try{
            QuestionData questionData = questionDataJpaRepository.findById(id).get();
            return mapperQuestion.toQuestion(questionData);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Question> findQuestionsByArea(Area area) {
        try{
            List<QuestionData> questionsData = questionDataJpaRepository.findByArea(area).get();

            List<Question> questions= questionsData.stream()
                    .map(questionData -> mapperQuestion.toQuestion(questionData))
                    .toList();
            return questions;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Question> findAllQuestions() {
        List<QuestionData> questionsData = questionDataJpaRepository.findAll();
        List<Question> questions= questionsData.stream()
                .map(questionData -> mapperQuestion.toQuestion(questionData))
                .toList();

        return questions;
    }

    @Override
    public Question updateQuestion(Question question) {
        //TODO Implementar update
        return null;
    }

    @Override
    public void deleteQuestionById(Long id) {
        questionDataJpaRepository.deleteById(id);
    }
}

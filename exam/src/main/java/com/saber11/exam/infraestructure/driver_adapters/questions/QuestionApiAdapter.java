package com.saber11.exam.infraestructure.driver_adapters.questions;

import com.saber11.exam.domain.model.Question;
import com.saber11.exam.domain.model.gateway.QuestionGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionApiAdapter implements QuestionGateway {

    private final WebClient webClient;


    @Override
    public List<Question> getQuestions() {
        return webClient
                .get()
                .uri("http://localhost:8080/api/saber11/question/getAll")
                .retrieve()
                .bodyToFlux(Question.class)
                .collectList()
                .block();
    }
}

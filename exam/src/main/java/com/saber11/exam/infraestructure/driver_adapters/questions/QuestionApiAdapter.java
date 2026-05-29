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
    String url= System.getenv("URL_ALL_QUESTIONS");

    @Override
    public List<Question> getQuestions() {
        return webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToFlux(Question.class)
                .collectList()
                .block();
    }
}

package com.saber11.exam.infraestructure.driver_adapters.questions;

import com.saber11.exam.domain.model.Question;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestionApiAdapterTest {

    private static final String TEST_URL = "http://test.url/questions";

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @BeforeAll
    static void setupEnv() {
        System.setProperty("URL_ALL_QUESTIONS", TEST_URL);
    }

    @AfterAll
    static void cleanupEnv() {
        System.clearProperty("URL_ALL_QUESTIONS");
    }

    @Test
    void getQuestionsReturnsListFromApi() {
        Question question1 = new Question();
        question1.setId(1L);
        Question question2 = new Question();
        question2.setId(2L);
        List<Question> expected = List.of(question1, question2);

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(TEST_URL)).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToFlux(Question.class)).thenReturn(Flux.just(question1, question2));

        QuestionApiAdapter adapter = new QuestionApiAdapter(webClient);
        List<Question> result = adapter.getQuestions();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());
        verify(webClient).get();
        verify(requestHeadersUriSpec).uri(TEST_URL);
        verify(requestHeadersSpec).retrieve();
        verify(responseSpec).bodyToFlux(Question.class);
    }

    @Test
    void constructorReadsEnvVar() {
        QuestionApiAdapter adapter = new QuestionApiAdapter(webClient);

        assertNotNull(adapter);
    }
}

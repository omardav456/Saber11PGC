package com.saber11.questions.infraestructure.entry_points;

import com.saber11.questions.domain.model.Area;
import com.saber11.questions.domain.model.Question;
import com.saber11.questions.domain.usecase.QuestionUseCase;
import com.saber11.questions.infraestructure.driver_adapters.jpa_repository.QuestionData;
import com.saber11.questions.infraestructure.mapper.MapperQuestion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/saber11/question")
@RequiredArgsConstructor

public class QuestionController {

    private final QuestionUseCase questionUseCase;
    private final MapperQuestion mapperQuestion;

    @PostMapping("/")
    public ResponseEntity<Question> createQuestion(@RequestBody QuestionData questionData){
        Question question = questionUseCase.createQuestion(mapperQuestion.toQuestion(questionData));
        return  ResponseEntity.ok(question);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> findQuestionById(@PathVariable Long id){
        Question question = questionUseCase.findQuestionById(id);
        return ResponseEntity.ok(question);
    }
    @GetMapping("/area/{area}")
    public ResponseEntity<List<Question>> findQuestionsByArea(@PathVariable Area area){
        return  ResponseEntity.ok(questionUseCase.findQuestionsByArea(area));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Question>> findAllQuestions(){
        return ResponseEntity.ok(questionUseCase.findAllQuestions());
    }

    @PutMapping("/")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
        return ResponseEntity.ok(questionUseCase.updateQuestion(question));
    }
    @DeleteMapping("/")
    public ResponseEntity deleteQuestionById(Long id){
        questionUseCase.deleteQuestionById(id);
        return ResponseEntity.ok().build();

    }


}

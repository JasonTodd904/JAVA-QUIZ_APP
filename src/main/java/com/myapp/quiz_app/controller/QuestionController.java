package com.myapp.quiz_app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.quiz_app.dto.QuestionDto;
import com.myapp.quiz_app.dto.QuestionRequestDto;
import com.myapp.quiz_app.dto.QuestionResponse;
import com.myapp.quiz_app.model.Question;
import com.myapp.quiz_app.service.QuestionService;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    //post mapping is empty cause it opens to basic url of request mapping and we dont need it for CURD opperations
    //we can use post mapping for submit
    @PostMapping
    public ResponseEntity<QuestionResponse> createQuestion(@RequestBody QuestionRequestDto request) {

        Question saved = questionService.create(request);//service returns question object

        QuestionResponse response =new QuestionResponse(true, "Question created successfully", saved);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<QuestionResponse> getAllQuestions() {
        List<QuestionDto> questions = questionService.getAllQuestions();

        QuestionResponse response =new QuestionResponse(true, "Questions fetched successfully", questions);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponse> getQuestionById(@PathVariable Long id) {

        QuestionDto question = questionService.getQuestionById(id);

        QuestionResponse response =new QuestionResponse(true, "Question fetched successfully", question);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionResponse> updateQuestion(@PathVariable Long id,@RequestBody QuestionRequestDto request) {

        Question updated = questionService.updateQuestion(id, request);

        QuestionResponse response =new QuestionResponse(true, "Question updated successfully", updated);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<QuestionResponse> deleteQuestion(@PathVariable Long id) {

        questionService.deleteQuestion(id);

        QuestionResponse response =new QuestionResponse(true, "Question deleted successfully", null);

        return ResponseEntity.ok(response);
    }
}
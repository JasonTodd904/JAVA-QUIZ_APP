package com.myapp.quiz_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.quiz_app.dto.QuizResultResponse;
import com.myapp.quiz_app.dto.QuizSubmitRequest;
import com.myapp.quiz_app.service.QuizService;

//This class will call QuizService
@RestController//Class handles requests from server
@RequestMapping("/api/quiz")//Base paths
public class QuizController {

    private final QuizService quizService;//obj of Quiz Service

    public QuizController(QuizService quizService) {//constructor injection
        this.quizService = quizService;
    }

    @PostMapping("/submit")
    public ResponseEntity<QuizResultResponse> submitQuiz(@RequestBody QuizSubmitRequest request) {

        QuizResultResponse result = quizService.submitQuiz(request);

        return ResponseEntity.ok(result);
    }
}
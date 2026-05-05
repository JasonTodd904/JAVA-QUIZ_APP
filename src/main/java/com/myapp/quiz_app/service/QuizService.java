package com.myapp.quiz_app.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.myapp.quiz_app.dto.QuizResultResponse;
import com.myapp.quiz_app.dto.QuizSubmitRequest;
import com.myapp.quiz_app.model.Question;
import com.myapp.quiz_app.repository.QuestionRepository;

@Service
public class QuizService {

    private final QuestionRepository questionRepository;

    public QuizService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public QuizResultResponse submitQuiz(QuizSubmitRequest request) {//dto class obj request

        int correctCount = 0;

        for (Map.Entry<Long, String> entry : request.getAnswers().entrySet()) {

            Long questionId = entry.getKey();
            String userAnswer = entry.getValue();

            Question question = questionRepository.findById(questionId).orElse(null);

            if (question != null && question.getCorrectAnswer().equalsIgnoreCase(userAnswer)) {//if answer is correct we increase the count
                correctCount++;
            }
        }

        int totalQuestions = request.getAnswers().size();//total questions=size of the map=total elements in the maps

        return new QuizResultResponse(totalQuestions, correctCount);//DTO class calculates the score as the cosntructor called here with new keyword
    }
}

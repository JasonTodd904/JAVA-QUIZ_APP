package com.myapp.quiz_app.dto;

public class QuizResultResponse {

    private int totalQuestions;
    private int correctAnswers;
    private int scorePercentage;

    public QuizResultResponse(int totalQuestions, int correctAnswers) {
        this.totalQuestions = totalQuestions;
        this.correctAnswers = correctAnswers;
        this.scorePercentage = (correctAnswers * 100) / totalQuestions;
    }

    public int getTotalQuestions() { return totalQuestions; }
    public int getCorrectAnswers() { return correctAnswers; }
    public int getScorePercentage() { return scorePercentage; }
    
}

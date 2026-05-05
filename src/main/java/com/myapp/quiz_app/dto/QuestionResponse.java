package com.myapp.quiz_app.dto;

//<T> --> it means the class work with any data type hence no need for type casting anywhere it is used
public class QuestionResponse<T> {

    private boolean success;
    private String message;
    private T data;

    public QuestionResponse() {}

    public QuestionResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
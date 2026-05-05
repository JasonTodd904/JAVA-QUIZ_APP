package com.myapp.quiz_app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.myapp.quiz_app.dto.QuestionDto;
import com.myapp.quiz_app.dto.QuestionRequestDto;
import com.myapp.quiz_app.model.Question;
import com.myapp.quiz_app.repository.QuestionRepository;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    //CREATE
    public Question create(QuestionRequestDto request)
    {
        Question question = new Question(
                        request.getCorrectAnswer(),
                        request.getOption1(), 
                        request.getOption2(),
                        request.getOption3(),
                        request.getOption4(),
                        request.getQuestionTitle());
        
        return questionRepository.save(question);
    }

    //READ
    public List<QuestionDto> getAllQuestions() {

        List<Question> questions = questionRepository.findAll();

        List<QuestionDto> dtoList = new ArrayList<>();

        //search in the question class and fetch all the ques and get everything except id and correctAnswer
        for (Question q : questions) {

            QuestionDto dto = new QuestionDto(
                    q.getId(),
                    q.getOption1(),
                    q.getOption2(),
                    q.getOption3(),
                    q.getOption4(),
                    q.getQuestionTitle()
            );

            dtoList.add(dto);//add the info in questionDto obj
        }

        return dtoList;//return QuestionDto obj
    }

    //Find by ID
    public QuestionDto getQuestionById(Long id)
    {
        Question question=questionRepository.findById(id).
        orElse(null);

        //make the constructor and then obj and return that obj
        return new QuestionDto(
                question.getId(),
                question.getQuestionTitle(),
                question.getOption1(),
                question.getOption2(),
                question.getOption3(),
                question.getOption4()
        );
    }

    //Update qeustion
    //questionRequestDto does have a correct option
    public Question updateQuestion(Long id, QuestionRequestDto request) {

        Question question = questionRepository.findById(id)//question is an entity so jpa returns its obj
                .orElseThrow(null);

        question.setQuestionTitle(request.getQuestionTitle());
        question.setOption1(request.getOption1());
        question.setOption2(request.getOption2());
        question.setOption3(request.getOption3());
        question.setOption4(request.getOption4());
        question.setCorrectAnswer(request.getCorrectAnswer());

        return questionRepository.save(question);
    }

    //DELETE
    public void deleteQuestion(Long id) {

        if (!questionRepository.existsById(id)) {
            throw new RuntimeException("Question not found with id: " + id);
        }

        questionRepository.deleteById(id);
    }
}

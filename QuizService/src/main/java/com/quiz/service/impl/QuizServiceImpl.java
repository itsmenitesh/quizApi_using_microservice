package com.quiz.service.impl;

import com.quiz.entities.Quiz;
import com.quiz.repository.IQuizRepository;
import com.quiz.service.QuestionClient;
import com.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {


    @Autowired
    private IQuizRepository iQuizRepository;

    @Autowired
    private QuestionClient  questionClient;
    @Override
    public Quiz add(Quiz quiz) {
        return iQuizRepository.save(quiz);
    }

    @Override
    public List<Quiz> get() {
        List<Quiz> quizzes = iQuizRepository.findAll();

        List<Quiz> newQuizList = quizzes.stream().map(quiz -> {
            quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getQuizId()));
            return quiz;
        }).collect(Collectors.toList());
        return  newQuizList;


    }

    @Override
    public Quiz get(Long id) {
        Quiz quiz = iQuizRepository.findById(id).orElseThrow(()-> new RuntimeException("Quiz with this id is not found"));
        quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getQuizId()));
        return quiz;

    }
}

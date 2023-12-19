package com.question.service.impl;

import com.question.entities.Question;
import com.question.repository.IQuestionRepository;
import com.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private IQuestionRepository iQuestionRepository;
    @Override
    public Question create(Question question) {
        return iQuestionRepository.save(question);
    }

    @Override
    public List<Question> get() {
        return iQuestionRepository.findAll();
    }

    @Override
    public Question getOne(Long id) {
        return iQuestionRepository.findById(id).orElseThrow(()-> new RuntimeException("Question by this id is not found"));
    }

    @Override
    public List<Question> getQuestionByQuizId(Long quizId) {
        return iQuestionRepository.findByQuizId(quizId);
    }
}

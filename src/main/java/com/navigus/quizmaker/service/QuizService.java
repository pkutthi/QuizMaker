package com.navigus.quizmaker.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.navigus.quizmaker.model.Quiz;
import com.navigus.quizmaker.model.Response;
import com.navigus.quizmaker.model.Result;

public interface QuizService {
	Quiz save(Quiz quiz);

	Page<Quiz> findAll(Pageable pageable);

	Page<Quiz> findAllPublished(Pageable pageable);

	Quiz find(Long id) ;

	Quiz update(Quiz quiz) ;

	void delete(Quiz quiz);

	Page<Quiz> search(String query, Pageable pageable);

	Result checkAnswers(Quiz quiz, List<Response> answersBundle);

	void publishQuiz(Quiz quiz);
}

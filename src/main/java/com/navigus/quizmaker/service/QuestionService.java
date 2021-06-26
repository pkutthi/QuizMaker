package com.navigus.quizmaker.service;

import java.util.List;

import com.navigus.quizmaker.model.Answer;
import com.navigus.quizmaker.model.Question;
import com.navigus.quizmaker.model.Quiz;

public interface QuestionService {
	Question save(Question question) ;

	Question find(Long id) ;

	List<Question> findQuestionsByQuiz(Quiz quiz);

	List<Question> findValidQuestionsByQuiz(Quiz quiz);

	Question update(Question question);

	void delete(Question question) ;

	Boolean checkIsCorrectAnswer(Question question, Long answer_id);

}

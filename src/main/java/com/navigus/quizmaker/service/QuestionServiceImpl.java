package com.navigus.quizmaker.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.navigus.quizmaker.model.Answer;
import com.navigus.quizmaker.model.Question;
import com.navigus.quizmaker.model.Quiz;
import com.navigus.quizmaker.repository.QuestionRepository;

@Service("QuestionService")
@Transactional
public class QuestionServiceImpl implements QuestionService {

	private static final Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);
	private QuestionRepository questionRepository;

	private AnswerService answerService;

	@Autowired
	public QuestionServiceImpl(QuestionRepository questionRepository, AnswerService answerService) {
		this.questionRepository = questionRepository;
		this.answerService = answerService;
	}

	@Override
	public Question save(Question question) {
		int count = questionRepository.countByQuiz(question.getQuiz());
		question.setOrder(count + 1);

		return questionRepository.save(question);
	}

	@Override
	public Question find(Long id) {
		Question question = questionRepository.findOne(id);

		if (question == null) {
			logger.error("Question " + id + " not found");
			throw new RuntimeException("Question " + id + " not found");
		}

		return question;
	}

	@Override
	public Question update(Question newQuestion) {
		Question currentQuestion = find(newQuestion.getId());

		mergeQuestions(currentQuestion, newQuestion);
		return questionRepository.save(currentQuestion);
	}

	@Override
	public void delete(Question question){
		Quiz quiz = question.getQuiz();

		if (quiz.getIsPublished()) {
			throw new RuntimeException("A published Quiz can't be updated");
		}

		questionRepository.delete(question);
	}

	private void mergeQuestions(Question currentQuestion, Question newQuestion) {
		currentQuestion.setText(newQuestion.getText());

		if (newQuestion.getOrder() != null)
			currentQuestion.setOrder(newQuestion.getOrder());
	}

	@Override
	public Boolean checkIsCorrectAnswer(Question question, Long answer_id) {
		Answer ans = answerService.find(answer_id);

		return ans.isCorrect();
	}

	@Override
	public List<Question> findQuestionsByQuiz(Quiz quiz) {
		return questionRepository.findByQuizOrderByOrderAsc(quiz);
	}

	@Override
	public List<Question> findValidQuestionsByQuiz(Quiz quiz) {
		return questionRepository.findByQuizAndIsValidTrueOrderByOrderAsc(quiz);
	}


}

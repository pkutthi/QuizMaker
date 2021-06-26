package com.navigus.quizmaker.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.navigus.quizmaker.model.Question;
import com.navigus.quizmaker.model.Quiz;
import com.navigus.quizmaker.model.Response;
import com.navigus.quizmaker.model.Result;
import com.navigus.quizmaker.repository.QuizRepository;

@Service("QuizService")
@Transactional
public class QuizServiceImpl implements QuizService {

	private static final Logger logger = LoggerFactory.getLogger(QuizServiceImpl.class);
	private QuizRepository quizRepository;

	private QuestionService questionService;

	@Autowired
	public QuizServiceImpl(QuizRepository quizRepository, QuestionService questionService) {
		this.quizRepository = quizRepository;
		this.questionService = questionService;
	}

	@Override
	public Quiz save(Quiz quiz) {
		return quizRepository.save(quiz);
	}

	@Override
	public Page<Quiz> findAll(Pageable pageable) {
		return quizRepository.findAll(pageable);
	}

	@Override
	public Page<Quiz> findAllPublished(Pageable pageable) {
		return quizRepository.findByIsPublishedTrue(pageable);
	}

	@Override
	public Quiz find(Long id) {
		Quiz quiz = quizRepository.findOne(id);

		if (quiz == null) {
			logger.error("Quiz " + id + " not found");
			throw new RuntimeException("Quiz " + id + " not found");
		}

		return quiz;
	}

	@Override
	public Quiz update(Quiz newQuiz){
		Quiz currentQuiz = find(newQuiz.getId());

		mergeQuizzes(currentQuiz, newQuiz);
		return quizRepository.save(currentQuiz);
	}

	@Override
	public void delete(Quiz quiz){
		quizRepository.delete(quiz);
	}

	private void mergeQuizzes(Quiz currentQuiz, Quiz newQuiz) {
		currentQuiz.setName(newQuiz.getName());
		currentQuiz.setDescription(newQuiz.getDescription());
	}

	@Override
	public Page<Quiz> search(String query, Pageable pageable) {
		return quizRepository.searchByName(query, pageable);
	}

	@Override
	public Result checkAnswers(Quiz quiz, List<Response> answersBundle) {
		Result results = new Result();

		for (Question question : quiz.getQuestions()) {
			boolean isFound = false;

			for (Response bundle : answersBundle) {
				if (bundle.getQuestion().equals(question.getId())) {
					isFound = true;
					results.addAnswer(questionService.checkIsCorrectAnswer(question, bundle.getSelectedAnswer()),question);
					break;
				}
			}

			if (!isFound) {
				throw new RuntimeException("No answer found for question: " + question.getText());
			}
		}

		if(results.getObtainMark() >= quiz.getCourse().getPassingMark()) {
			results.setResult("PASS");
		}
		return results;
	}

	@Override
	public void publishQuiz(Quiz quiz) {

			quiz.setIsPublished(true);
			quizRepository.save(quiz);
	}

}

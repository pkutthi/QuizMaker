package com.navigus.quizmaker.controller.rest.v1;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.navigus.quizmaker.model.Answer;
import com.navigus.quizmaker.model.Question;
import com.navigus.quizmaker.model.Quiz;
import com.navigus.quizmaker.service.AnswerService;
import com.navigus.quizmaker.service.QuestionService;
import com.navigus.quizmaker.service.QuizService;

@RestController
@RequestMapping(QuestionController.ROOT_MAPPING)
public class QuestionController {

	public static final String ROOT_MAPPING = "/api/questions";

	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuizService quizService;

	@Autowired
	private AnswerService answerService;

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Question save(@RequestBody Question question, @RequestParam Long quiz_id) {

		Quiz quiz = quizService.find(quiz_id);
		question.setQuiz(quiz);

		return questionService.save(question);
	}

	@RequestMapping(value = "/updateAll", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void updateAll(@RequestBody List<Question> questions) {
		for (int i = 0; i < questions.size(); i++) {
			Question question = questions.get(i);
			question.setOrder(i + 1);

			questionService.update(question);
		}
	}

	@RequestMapping(value = "/{question_id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Question find(@PathVariable Long question_id) {

		return questionService.find(question_id);
	}

	@RequestMapping(value = "/{question_id}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public Question update(@PathVariable Long question_id, @RequestBody Question question, BindingResult result) {

		question.setId(question_id);
		return questionService.update(question);

	}

	@RequestMapping(value = "/{question_id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long question_id) {
		Question question = questionService.find(question_id);
		questionService.delete(question);
	}
	

}

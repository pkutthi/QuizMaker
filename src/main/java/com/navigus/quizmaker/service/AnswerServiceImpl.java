package com.navigus.quizmaker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.navigus.quizmaker.model.Answer;
import com.navigus.quizmaker.repository.AnswerRepository;

@Service("AnswerService")
@Transactional
public class AnswerServiceImpl implements AnswerService {

	private static final Logger logger = LoggerFactory.getLogger(AnswerServiceImpl.class);
	private AnswerRepository answerRepository;

	@Autowired
	public AnswerServiceImpl(AnswerRepository answerRepository) {
		this.answerRepository = answerRepository;
	}

	@Override
	public Answer find(Long id)  {
		Answer answer = answerRepository.findOne(id);

		if (answer == null) {
			logger.error("Answer " + id + " not found");
			throw new RuntimeException("Answer " + id + " not found");
		}

		return answer;
	}

	@Override
	public Answer save(Answer answer) {
		return answerRepository.save(answer);
	}

	@Override
	public Answer update(Answer newAnswer){
		Answer currentAnswer = find(newAnswer.getId());

		mergeAnswers(currentAnswer, newAnswer);
		return answerRepository.save(currentAnswer);
	}

	@Override
	public void delete(Answer answer) {
		answerRepository.delete(answer);
	}

	private void mergeAnswers(Answer currentAnswer, Answer newAnswer) {
		currentAnswer.setText(newAnswer.getText());

		if (newAnswer.getOrder() != null) {
			currentAnswer.setOrder(newAnswer.getOrder());
		}
	}

}

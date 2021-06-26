package com.navigus.quizmaker.service;

import java.util.List;

import com.navigus.quizmaker.model.Answer;
import com.navigus.quizmaker.model.Question;

public interface AnswerService {
	Answer save(Answer answer);

	Answer find(Long id);

	Answer update(Answer newAnswer);

	void delete(Answer answer);
}

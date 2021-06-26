package com.navigus.quizmaker.model;

public class Result {
	private int totalQuestions = 0;
	private int correctQuestions = 0;
	private int maxMarks = 0;
	private int obtainMark = 0;
	private String result = "FAIL";

	public int getTotalQuestions() {
		return totalQuestions;
	}

	public void setTotalQuestions(int totalQuestions) {
		this.totalQuestions = totalQuestions;
	}

	public int getCorrectQuestions() {
		return correctQuestions;
	}

	public void setCorrectQuestions(int correctQuestions) {
		this.correctQuestions = correctQuestions;
	}

	public void addAnswer(Boolean isCorrect,Question question) {
		totalQuestions++;
		maxMarks = maxMarks + question.getMark();
		if (isCorrect) {
			correctQuestions++;
			obtainMark = obtainMark + question.getMark();
		}
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(int maxMarks) {
		this.maxMarks = maxMarks;
	}

	public int getObtainMark() {
		return obtainMark;
	}

	public void setObtainMark(int obtainMark) {
		this.obtainMark = obtainMark;
	}
	
}

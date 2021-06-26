package com.navigus.quizmaker.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "question")
public class Question extends BaseModel {

	@Size(min = 2, max = 150, message = "The question should be between 2 and 150 characters")
	@NotNull(message = "Question text not provided")
	private String text;

	@ManyToOne
	@JsonIgnore
	private Quiz quiz;

	@Column(name = "q_order")
	private Integer order;

	@Column(name = "mark")
	private Integer mark;
	
	//@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="q_id", referencedColumnName="id")
	private List<Answer> answers;


	@Column( insertable = false, updatable = false)
	private Calendar createdDate;

	@JsonIgnore
	private Boolean isValid = false;

	public Calendar getCreatedDate() {
		return createdDate;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}


	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}


}

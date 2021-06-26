package com.navigus.quizmaker.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "course")
public class Course extends BaseModel{


	@Size(min = 2, max = 100, message = "The name must be between 2 and 100 messages.")
	@NotNull(message = "Please provide a name")
	private String name;

	@Size(max = 500, message = "The description can't be longer than 500 characters.")
	@NotNull(message = "Please, provide a description")
	private String description;
	

	@Column( insertable = false, updatable = false)
	private Calendar createdDate;

	@Column(name="PASSING_MARK")
	private int passingMark;
	
	public Calendar getCreatedDate() {
		return createdDate;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPassingMark() {
		return passingMark;
	}

	public void setPassingMark(int passingMark) {
		this.passingMark = passingMark;
	}

	
	
}

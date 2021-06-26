package com.navigus.quizmaker.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.navigus.quizmaker.model.Course;

public interface CourseService {
	Course save(Course quiz);

	Page<Course> findAll(Pageable pageable);

	Course find(Long id) ;

	Course update(Course quiz) ;

	void delete(Course quiz);

}

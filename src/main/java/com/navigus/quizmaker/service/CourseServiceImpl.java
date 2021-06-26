package com.navigus.quizmaker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.navigus.quizmaker.model.Course;
import com.navigus.quizmaker.repository.CourseRepository;

@Service("CourseService")
@Transactional
public class CourseServiceImpl implements CourseService {

	private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
	
	private CourseRepository courseRepository;


	@Autowired
	public CourseServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@Override
	public Course save(Course course) {
		return courseRepository.save(course);
	}

	@Override
	public Course find(Long id) {
		Course course = courseRepository.findOne(id);

		if (course == null) {
			logger.error("course " + id + " not found");
			throw new RuntimeException("course " + id + " not found");
		}

		return course;
	}

	@Override
	public Course update(Course quiz) {
		Course currentQuiz = find(quiz.getId());
		return courseRepository.save(currentQuiz);
	}

	@Override
	public void delete(Course quiz) {
		courseRepository.delete(quiz);
		
	}

	@Override
	public Page<Course> findAll(Pageable pageable) {
		return courseRepository.findAll(pageable);
	}


}

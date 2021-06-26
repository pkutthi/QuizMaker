package com.navigus.quizmaker.controller.rest.v1;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.navigus.quizmaker.model.Course;
import com.navigus.quizmaker.service.CourseService;

@RestController
@RequestMapping(CourseController.ROOT_MAPPING)
public class CourseController {

	public static final String ROOT_MAPPING = "/api/course";
	
	private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

	@Autowired
	private CourseService courseService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Page<Course> findAll(Pageable pageable) {
		
			return courseService.findAll(pageable);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Course save(@RequestBody Course course) {

		logger.debug("The Course " + course.getName() + " is going to be created");
		return courseService.save(course);
	}

	@RequestMapping(value = "/{course_id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Course find(@PathVariable Long course_id) {

		return courseService.find(course_id);
	}

	@RequestMapping(value = "/{course_id}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public Course update(@PathVariable Long course_id, @RequestBody Course course) {

		course.setId(course_id);
		return courseService.update(course);
	}

	@RequestMapping(value = "/{course_id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long course_id) {
		Course course = courseService.find(course_id);
		courseService.delete(course);
	}

}

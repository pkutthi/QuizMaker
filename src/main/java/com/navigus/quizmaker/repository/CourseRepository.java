package com.navigus.quizmaker.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.navigus.quizmaker.model.Course;
import com.navigus.quizmaker.model.Quiz;

@Repository("CourseRepository")
public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {


	@Query("select q from Course q where q.name like %?1%")
	Page<Quiz> searchByName(String name, Pageable pageable);
}

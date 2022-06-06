package com.neighborpil.jpa.hibernate.demo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.neighborpil.jpa.hibernate.demo.entity.Course;
import com.neighborpil.jpa.hibernate.demo.repository.CourseSpringDataRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(classes=DemoApplication.class)
class CourseSpringRepositoryTest {

	@Autowired
	CourseSpringDataRepository repository;
	
	@Test
	public void findById_CoursePresent() {
		Optional<Course> courseOptional = repository.findById(10001L);
		assertTrue(courseOptional.isPresent());
		log.info("{}",  courseOptional.isPresent());
	}
	
	@Test
	public void findById_CourseNotPresent() {
		Optional<Course> courseOptional = repository.findById(20001L);
		assertFalse(courseOptional.isPresent());
		log.info("{}",  courseOptional.isPresent());
	}

	@Test
	public void playingAroungWithSpringDataRepository() {
		Course course = new Course("Microservices in 100 steps");
		repository.save(course);
		course.setName("Microservices in 100 steps - updated");
		repository.save(course);
		
		log.info("Courses -> {}", repository.findAll());
		log.info("Courses -> {}", repository.count());
		
	}

	@Test
	public void sort() {
		
		Sort sort = Sort.by(Sort.Direction.DESC, "name");
		
		log.info("Sorted Courses -> {}", repository.findAll(sort));
		log.info("Courses -> {}", repository.count());
		
	}

	@Test
	public void two_sort() {
		
		Sort sort = Sort.by(Sort.Direction.DESC, "name").and(Sort.by(Sort.Direction.DESC, "students"));
		
		log.info("Sorted Courses -> {}", repository.findAll(sort));
		log.info("Courses -> {}", repository.count());
		
	}
	
	@Test
	public void pagination() {
		PageRequest pageRequest = PageRequest.of(0, 3);
		
		Page<Course> firstPage = repository.findAll(pageRequest);
		log.info("First Page => {}", firstPage);
		
		Pageable secondPageable = firstPage.nextPageable();
		Page<Course> secondPage = repository.findAll(secondPageable);
		log.info("Second Page => {}", secondPage);
	}

	@Test
	public void findUsingName() {
		log.info("FindByName -> {}", repository.findByName("JPA in 50 steps"));
	}
}

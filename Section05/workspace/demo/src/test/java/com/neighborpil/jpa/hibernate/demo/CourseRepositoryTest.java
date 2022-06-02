package com.neighborpil.jpa.hibernate.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.neighborpil.jpa.hibernate.demo.entity.Course;
import com.neighborpil.jpa.hibernate.demo.repository.CourseRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(classes=DemoApplication.class)
class CourseRepositoryTest {

	@Autowired
	CourseRepository repository;
	
	@Test
	void findById_basic() {
		Course course = repository.findById(10001L);
		
		assertEquals("JPA in 50 steps", course.getName());
	}
	
	@Test
	@DirtiesContext // spring will automatically reset the used data
	void deleteById_basic() {
		repository.deleteById(10002L);
		
		assertNull(repository.findById(10002L));
	}
	
	@Test
	@DirtiesContext // spring will automatically reset the used data
	void save_basic() {
		// get a course
		Course course = repository.findById(10001L);
		assertEquals("JPA in 50 steps", course.getName());
		// update details
		course.setName("JPA in 50 steps - Updated");
		repository.save(course);
		
		// check the value
		Course course1 = repository.findById(10001L);
		assertEquals("JPA in 50 steps - Updated", course1.getName());
	}
	
	@Test
	@DirtiesContext
	void playWithEntityManager() {
		repository.playWithEntityManager();
	}

}

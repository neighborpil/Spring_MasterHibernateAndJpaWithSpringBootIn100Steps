package com.neighborpil.jpa.hibernate.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.neighborpil.jpa.hibernate.demo.entity.Course;
import com.neighborpil.jpa.hibernate.demo.entity.Review;
import com.neighborpil.jpa.hibernate.demo.repository.CourseRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(classes=DemoApplication.class)
class CourseRepositoryTest {

	@Autowired
	CourseRepository repository;

	@Autowired
	EntityManager em;
	
	@Test
	void findById_basic() {
		Course course = repository.findById(10001L);
		
		assertEquals("JPA in 50 steps", course.getName());
	}
		
	@Test
	@Transactional
	void findById_firstLevelCacheDemo() {
		Course course = repository.findById(10001L);
		log.info("First Course Retrieved: {}", course);
		Course course1 = repository.findById(10001L); // first level caching occurred. It was done because of same boundary of transaction
		log.info("First Course Retrieved again: {}", course1);
		
		assertEquals("JPA in 50 steps", course.getName());
		assertEquals("JPA in 50 steps", course1.getName());
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

	@Test
	@Transactional
	void retrieveReviewsForCourse() {
		Course course = repository.findById(10001L);
		log.info("{}", course.getReviews());
	}
	
	@Test
//	@Transactional
	@Transactional(isolation = Isolation.READ_COMMITTED)
//	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
//	@Transactional(isolation = Isolation.REPEATABLE_READ)
//	@Transactional(isolation = Isolation.SERIALIZABLE)
	void retrieveCourseForReviews() {
		
		// scenario that uses multiple databases
		// At that scenario I should use springframework transaction
		// database 1
		  // update 1
		  // update 2
		
		// database 2
		
		// mq
		
		
		Review review = em.find(Review.class, 50001L);
		log.info("{}", review.getCourse());
	}

	
}

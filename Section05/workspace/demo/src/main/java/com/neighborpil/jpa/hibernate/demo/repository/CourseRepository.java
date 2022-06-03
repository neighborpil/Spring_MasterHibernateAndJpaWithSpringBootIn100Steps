package com.neighborpil.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.neighborpil.jpa.hibernate.demo.entity.Course;
import com.neighborpil.jpa.hibernate.demo.entity.Review;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@Transactional
public class CourseRepository {

	@Autowired
	EntityManager em;
	
	public Course findById(Long id) {
		return em.find(Course.class, id);
	}
	
	public Course save(Course course) {
		if(course.getId() == null) {
			em.persist(course);
		} else {
			em.merge(course);
		}
		
		return course;
	}
	
	 public void deleteById(Long id) {
		 Course course = findById(id);
		 em.remove(course);
	 }
	 
	 public void playWithEntityManager() {
		 
		 Course course1 = new Course("Web Services in 100 steps");
		 em.persist(course1);
		 
		 Course course2 = findById(10002L);
		 course2.setName("JPA in 50 steps - Updated");
		 /*
		 // Codes for detach and clear
		 Course course1 = new Course("Web Services in 100 steps");
		 em.persist(course1);
		 Course course2 = new Course("Angular JS in 100 steps");
		 em.persist(course2);
		 em.flush(); // DB에 반영

//		 em.detach(course1); // db와의 연결을 끊어버림
//		 em.clear();

		 course1.setName("Web Services in 100 steps - updated");
		 course2.setName("Angular JS in 100 steps - updated");
		 em.refresh(course1); // entity manager는 변경사항을 트래킹 하고 있는데 이때까지의 변경사항을 리프레시 함
		 
		 
		 em.flush(); // DB에 반영
		 */
	 }

	public void addHardcodedReviewsForCourse() {
		
		// get the review 1003
		 Course course = findById(10003L);
		 log.info("course.getReviews() -> {}", course.getReviews());
		
		// add 2 reviews to it
		 Review review1 = new Review("5", "Greate Hands-on Stuff.");
		 Review review2 = new Review("5", "Hands-on");
		
		 // setting the relationship
		 course.addReview(review1);
		 review1.setCourse(course);
		 
		 course.addReview(review2);
		 review2.setCourse(course);
		 
		// save it to the database
		 em.persist(review1);
		 em.persist(review2);
		 
	}
	
	public void addReviewsForCourse(Long courseId, List<Review> reviews) {
		
		 Course course = findById(courseId);
		 log.info("course.getReviews() -> {}", course.getReviews());
		
		 for(Review review : reviews) {
			
			 // setting the relationship
			 course.addReview(review);
			 review.setCourse(course);
			 em.persist(review);
		 }
		 
	}
}

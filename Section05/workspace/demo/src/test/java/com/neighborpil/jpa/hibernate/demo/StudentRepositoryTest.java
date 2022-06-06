package com.neighborpil.jpa.hibernate.demo;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import com.neighborpil.jpa.hibernate.demo.entity.Address;
import com.neighborpil.jpa.hibernate.demo.entity.Passport;
import com.neighborpil.jpa.hibernate.demo.entity.Student;
import com.neighborpil.jpa.hibernate.demo.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(classes=DemoApplication.class)
class StudentRepositoryTest {

	@Autowired
	StudentRepository repository;
	
	@Autowired
	EntityManager em;
	
	@Test
	@Transactional
	void retrieveStudentAndPassportDetails() {
		Student student = em.find(Student.class, 20001L);
		log.info("student -> {}", student);
		log.info("passport -> {}", student.getPassport());
	}

	@Test
	@Transactional // Persistence Context
	@DirtiesContext
	void someTest() {
		repository.someOperationToUnderstandPersistenceContext();
	}

	@Test
	@Transactional
	@DirtiesContext
	void retrievePassportAndAssociatedStudent() {
		Passport passport = em.find(Passport.class, 40001L);
		log.info("passport -> {}", passport);
		log.info("student -> {}", passport.getStudent());
	}	

	@Test
	@Transactional
	@DirtiesContext
	void retrieveStudentAndCourses() {
		Student student = em.find(Student.class, 20001L); // lazy fetch
		log.info("student -> {}", student);
		log.info("student.getCourses() -> {}", student.getCourses());
	}	

	@Test
	@Transactional
	@DirtiesContext
	void setAddressDetails() {
		Student student = em.find(Student.class, 20001L);
		student.setAddress(new Address("No 101", "Some Street", "Busan"));		
		em.flush();
		log.info("student -> {}", student);
	}	


}

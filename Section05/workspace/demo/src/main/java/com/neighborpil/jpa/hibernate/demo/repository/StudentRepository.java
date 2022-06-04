package com.neighborpil.jpa.hibernate.demo.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.neighborpil.jpa.hibernate.demo.entity.Course;
import com.neighborpil.jpa.hibernate.demo.entity.Passport;
import com.neighborpil.jpa.hibernate.demo.entity.Student;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@Transactional
public class StudentRepository {

	@Autowired
	EntityManager em;
	
	public Student findById(Long id) {
		return em.find(Student.class, id);
	}
	
	public Student save(Student student) {
		if(student.getId() == null) {
			em.persist(student);
		} else {
			em.merge(student);
		}
		
		return student;
	}
	
	 public void deleteById(Long id) {
		 Student student = findById(id);
		 em.remove(student);
	 }
	 
	 public void saveStudentWithPassport() {
		Passport passport = new Passport("Z123456");
		em.persist(passport);
		Student student = new Student("Mike", passport);
		em.persist(student);
	 }
	 
	 // @Transactional
	 public void someOperationToUnderstandPersistenceContext() {
			// Database Operation 1 - Retrive student
			Student student = em.find(Student.class, 20001L);
			// Persistence Context (student) - That's what really doing
			
			// Database Operation 2 - Retrive passport
			Passport passport = student.getPassport();
			// Persistence Context (student, passport)
			
			// Database Operation 3 - update passport 
			passport.setNumber("E1234567");
			// Persistence Context (student, passport++)

			// Database Operation 4 - update student 
			student.setName("Feel - updated");
			// Persistence Context (student++, passport++)
		}
	 
	 public void insertHardCodedStudentAndCourse() {
		 Student student = new Student("Jack");
		 Course course = new Course("Microservices in 100 steps");
		 em.persist(student);
		 em.persist(course);
		 
		 student.addCourse(course);
		 course.addStudent(student);
		 
		 em.persist(student);
	 }

	 public void insertStudentAndCourse(Student student, Course course) {
		 em.persist(student);
		 em.persist(course);
		 
		 student.addCourse(course);
		 course.addStudent(student);
		 
		 em.persist(student);
	 }

}

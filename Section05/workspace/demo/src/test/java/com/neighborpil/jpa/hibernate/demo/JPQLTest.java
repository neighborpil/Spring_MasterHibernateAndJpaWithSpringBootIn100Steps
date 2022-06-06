package com.neighborpil.jpa.hibernate.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.neighborpil.jpa.hibernate.demo.entity.Course;
import com.neighborpil.jpa.hibernate.demo.entity.Student;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(classes=DemoApplication.class)
class JPQLTest {

	@Autowired
	EntityManager em;
	
	@Test
	void jpql_basic() {
//		Query createQuery = em.createQuery("SELECT c FROM Course c");
		TypedQuery<Course> createNamedQuery = em.createNamedQuery("query_get_all_courses", Course.class);
		List resultList = createNamedQuery.getResultList();
		log.info("SELECT c FROM course c -> {}", resultList);
	}

	@Test
	void jpql_typed() {
//		TypedQuery<Course> createQuery = em.createQuery("SELECT c FROM Course c", Course.class);
		TypedQuery<Course> createNamedQuery = em.createNamedQuery("query_get_all_courses", Course.class);
		List<Course> resultList = createNamedQuery.getResultList();
		log.info("SELECT c FROM course c -> {}", resultList);
	}
	
	@Test
	void jpql_where() {
		TypedQuery<Course> createQuery = em.createNamedQuery("query_get_100_courses", Course.class);
		List<Course> resultList = createQuery.getResultList();
		log.info("SELECT c FROM course c WHERE name like '%100 steps' -> {}", resultList);
	}

	@Test
	void jpql_courses_without_students() {
		TypedQuery<Course> createQuery = em.createQuery("Select c from Course c where c.students is empty", Course.class);
		List<Course> resultList = createQuery.getResultList();
		log.info("Results -> {}", resultList);
	}

	@Test
	void jpql_courses_with_atleast_2_students() {
		TypedQuery<Course> createQuery = em.createQuery("Select c from Course c where size(c.students) >= 2", Course.class);
		List<Course> resultList = createQuery.getResultList();
		log.info("Results -> {}", resultList);
	}

	@Test
	void jpql_courses_ordered_by_students() {
		TypedQuery<Course> createQuery = em.createQuery("Select c from Course c order by size(c.students) desc", Course.class);
		List<Course> resultList = createQuery.getResultList();
		log.info("Results -> {}", resultList);
	}

	@Test
	@Transactional
	void jpql_students_with_passports_in_a_certain_pattern() {
		TypedQuery<Student> createQuery = em.createQuery("Select s from Student s where s.passport.number like '%1234%'", Student.class);
		List<Student> resultList = createQuery.getResultList();
		log.info("Results -> {}", resultList);
		
		
	}
	/* 
	// Options
	LIKE
	BETWEEN 10 AND 1000
	IS NULL
	UPPPER, LOWER, TRIM, LENGTH
	
	JOIN -> Select c, s from Course c JOIN c.students s 
	LEFT JOIN -> Select c, s from Course c LEFT JOIN c.students s
	CROSS JOIN -> Select c, s from Course c, Student s // 3 and 4 => 3 * 4 = 12 Rows
	
	*/

	@Test
	@Transactional
	public void join()  {
		Query query = em.createQuery("Select c, s from Course c JOIN c.students s");
		List<Object[]> resultList = query.getResultList();
		log.info("Results -> {}", resultList.size());
		
		for(Object[] result : resultList) {
			log.info("Course: {}, Student: {}", result[0], result[1]);
		}
		
	}

	@Test
	@Transactional
	public void left_join()  {
		Query query = em.createQuery("Select c, s from Course c LEFT JOIN c.students s");
		List<Object[]> resultList = query.getResultList();
		log.info("Results -> {}", resultList.size());
		
		for(Object[] result : resultList) {
			log.info("Course: {}, Student: {}", result[0], result[1]);
		}
		
	}

	@Test
	@Transactional
	public void cross_join()  {
		Query query = em.createQuery("Select c, s from Course c, Student s");
		List<Object[]> resultList = query.getResultList();
		log.info("Results -> {}", resultList.size());
		
		for(Object[] result : resultList) {
			log.info("Course: {}, Student: {}", result[0], result[1]);
		}
		
	}
}

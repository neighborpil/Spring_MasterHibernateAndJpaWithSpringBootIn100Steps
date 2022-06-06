package com.neighborpil.jpa.hibernate.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.neighborpil.jpa.hibernate.demo.entity.Course;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(classes=DemoApplication.class)
class CriteriaQueryTest {

	@Autowired
	EntityManager em;
	
	@Test
	void all_courses() {
		
		// "SELECT c FROM Course c"
		
		// 1. Use Criteria Builder to create a Criteria Query returning the expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		// 2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		// 3. Define Predicates etc using Criteria Builder
		
		// 4. Add Predicates etc to the Criteria Query
		
		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		log.info("Typed Query -> {}", resultList);
	}
	
	@Test
	void all_courses_having_100steps() {
		
		// "SELECT c FROM Course c WHERE name like '%100 steps'"
		
		// 1. Use Criteria Builder to create a Criteria Query returning the expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		// 2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		// 3. Define Predicates etc using Criteria Builder
		Predicate like100steps = cb.like(courseRoot.get("name"), "%100 steps");
		
		// 4. Add Predicates etc to the Criteria Query
		cq.where(like100steps);
		
		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		log.info("Typed Query -> {}", resultList);
	}
	
	@Test
	void all_courses_without_students() {
		
		// "SELECT c FROM Course c WHERE c.students is empty"
		
		// 1. Use Criteria Builder to create a Criteria Query returning the expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		// 2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		// 3. Define Predicates etc using Criteria Builder
		Predicate studentsIsEmpty = cb.isEmpty(courseRoot.get("students"));
		
		// 4. Add Predicates etc to the Criteria Query
		cq.where(studentsIsEmpty);
		
		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		log.info("Typed Query -> {}", resultList);
	}
	
	@Test
	void join() {
		
		// "SELECT c FROM Course c JOIN c.students s"
		
		// 1. Use Criteria Builder to create a Criteria Query returning the expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		// 2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		// 3. Define Predicates etc using Criteria Builder
		Join<Object, Object> join = courseRoot.join("students");
		
		// 4. Add Predicates etc to the Criteria Query
		
		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		log.info("Typed Query -> {}", resultList);
	}
	
	
	@Test
	void left_join() {
		
		// "SELECT c FROM Course c JOIN c.students s"
		
		// 1. Use Criteria Builder to create a Criteria Query returning the expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		// 2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		// 3. Define Predicates etc using Criteria Builder
		Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);
		
		// 4. Add Predicates etc to the Criteria Query
		
		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		log.info("Typed Query -> {}", resultList);
	}
}
